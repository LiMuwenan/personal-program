package cn.ligen.server.bill.service.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.ligen.server.bill.entity.mapper.BillEntityStruct;
import cn.ligen.server.bill.entity.po.BillBookItem;
import cn.ligen.server.bill.entity.po.BillCategory;
import cn.ligen.server.bill.entity.po.BillEntity;
import cn.ligen.server.bill.entity.query.BillQuery;
import cn.ligen.server.bill.entity.vo.BillDetailVo;
import cn.ligen.server.bill.entity.vo.OverViewVo;
import cn.ligen.server.bill.mapper.BillBookItemMapper;
import cn.ligen.server.bill.mapper.BillMapper;
import cn.ligen.server.bill.service.BillService;
import cn.ligen.server.common.exception.BadRequestException;
import cn.ligen.server.common.util.UserContextHolder;
import cn.ligen.server.redis.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ligen
 * @date 2023/9/2 23:30
 * @description
 */
@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillMapper billMapper;
    private final RedisUtil redisUtil;
    private final BillBookItemMapper billBookItemMapper;

    @Override
    @Transactional
    public Integer addBill(BillEntity bill, Set<Integer> billBooks) {
        bill.setCreateTime(LocalDateTime.now());
        BillCategory category = (BillCategory) redisUtil.get(String.valueOf(bill.getCode()));
        bill.setMessage(category.getMessage());
        bill.setFlag(category.getStatus());
        Map<String, Object> user = (Map<String, Object>) UserContextHolder.getUser();
        bill.setUserId((Integer) user.get("id"));
        int cnt = billMapper.insert(bill);
        saveBatchBookItem(bill.getId(), billBooks);
        return cnt;
    }

    private void saveBatchBookItem(Integer itemId, Set<Integer> billBooks) {
        List<BillBookItem> bookItems = new ArrayList<>(billBooks.size());
        billBooks.forEach(id->bookItems.add(new BillBookItem(id, itemId)));
        billBookItemMapper.saveBatch(bookItems);
    }

    @Override
    public Integer importBillList(List<BillEntity> billList) {
        LocalDateTime now = LocalDateTime.now();
        for (BillEntity bill : billList) {
            bill.setCreateTime(now);
            billMapper.insert(bill);
        }
        return billList.size();
    }

    @Override
    public List<BillEntity> queryBillList(BillQuery query, Page<BillEntity> page) {
        Integer id = (Integer) ((Map<String, Object>) UserContextHolder.getUser()).get("id");
        List<BillEntity> billEntities = billMapper.selectPage(id, query, (page.getCurrent() - 1) * page.getSize(), page.getSize());
        page.setTotal(billMapper.selectPageCount(id, query));
        return  billEntities;
    }

    @Override
    public BillDetailVo queryBillDetail(Integer id) {
        BillEntity billEntity = billMapper.selectById(id);
        List<BillBookItem> bookItems = billBookItemMapper.selectList(new LambdaQueryWrapper<BillBookItem>()
                .eq(BillBookItem::getItemId, id).orderByAsc(BillBookItem::getItemId));
        BillDetailVo detailVo = BillEntityStruct.INSTANCE.toDetailVo(billEntity);
        detailVo.setBooksId(bookItems.stream().map(BillBookItem::getBookId).collect(Collectors.toList()));
        return detailVo;
    }

    public OverViewVo billStat(BillQuery query) {
        List<BillEntity> list = billMapper.selectList(
                new LambdaQueryWrapper<BillEntity>()
                        .in(query.getCodes() != null, BillEntity::getCode, query.getCodes())
                        .le(query.getHighCost() != null, BillEntity::getCost, query.getHighCost())
                        .ge(query.getLowCost() != null, BillEntity::getCost, query.getLowCost())
                        .le(query.getEndTime() != null, BillEntity::getCostTime, query.getEndTime())
                        .ge(query.getStartTime() != null, BillEntity::getCostTime, query.getStartTime())
        );

        OverViewVo vo = new OverViewVo();
        // 总收入
        BigDecimal income = new BigDecimal(0);
        // 总支出
        BigDecimal spend = new BigDecimal(0);
        // 按类别金额
        List<Map<String, BigDecimal>> groupByCode = new ArrayList<>();
        Map<String, BigDecimal> flag0Map = new HashMap<>();
        Map<String, BigDecimal> flag1Map = new HashMap<>();
        groupByCode.add(flag0Map);
        groupByCode.add(flag1Map);
        // 按日期分类
        String format = "yyyy-MM-dd";
        if (query.getStartTime().getYear() != query.getEndTime().getYear()
            || query.getStartTime().getMonthValue() != query.getEndTime().getMonthValue()) {
            format = "yyyy-MM";
        }
        // key: 日期
        // value:
        //      key: 支出或收入, false收入，true支出
        //      value: 金额
        Map<String, Map<Integer, BigDecimal>> groupByDate = new HashMap<>();
        for (BillEntity bill : list) {
            Map<Integer, BigDecimal> dateGroup = groupByDate.getOrDefault(LocalDateTimeUtil.format(bill.getCostTime(), format), new HashMap<>());
            BigDecimal tmp = dateGroup.getOrDefault(bill.getFlag(), BigDecimal.ZERO);
            tmp = tmp.add(bill.getCost());
            dateGroup.put(bill.getFlag(), tmp);
            groupByDate.put(LocalDateTimeUtil.format(bill.getCostTime(), format), dateGroup);
            if (bill.getFlag() == 1) {
                // 支出
                spend = spend.add(bill.getCost());
            } else {
                // 收入
                income = income.add(bill.getCost());
            }
            // 分类总金额
            groupByCode.get(bill.getFlag()).merge(bill.getMessage(), bill.getCost(), BigDecimal::add);
//            BigDecimal sum = groupByCode.getOrDefault(bill.getMessage(), BigDecimal.ZERO);
//            sum = sum.add(bill.getCost());
//            groupByCode.put(bill.getMessage(), sum);
        }

        vo.setIncome(income);
        vo.setSpend(spend);
        vo.setBalance(income.subtract(spend));
        vo.setGroupByCode(groupByCode);
        vo.setGroupByDate(groupByDate);

        return vo;
    }

    @Override
    @Transactional
    public void updateBill(BillEntity bill, Set<Integer> billBookItems) {
        BillCategory category = (BillCategory) redisUtil.get(String.valueOf(bill.getCode()));
        if (category == null) {
            throw new BadRequestException("选择种类编码错误");
        }
        bill.setMessage(category.getMessage());
        billMapper.updateById(bill);
        clearAndInsert(bill.getId(), billBookItems);
    }

    private void clearAndInsert(Integer itemId, Set<Integer> billBooks) {
        billBookItemMapper.delete(new LambdaQueryWrapper<BillBookItem>().eq(BillBookItem::getItemId, itemId));
        saveBatchBookItem(itemId, billBooks);
    }

    @Override
    public void deleteBill(List<Integer> ids) {
        billMapper.delete(
                new LambdaQueryWrapper<BillEntity>()
                        .in(BillEntity::getId, ids)
        );
    }
}
