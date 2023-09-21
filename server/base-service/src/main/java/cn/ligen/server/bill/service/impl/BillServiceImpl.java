package cn.ligen.server.bill.service.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.ligen.server.bill.entity.BillCategoryEnum;
import cn.ligen.server.bill.entity.BillEntity;
import cn.ligen.server.bill.entity.query.BillQuery;
import cn.ligen.server.bill.entity.query.OverViewQuery;
import cn.ligen.server.bill.entity.vo.OverViewVo;
import cn.ligen.server.bill.mapper.BillMapper;
import cn.ligen.server.bill.service.BillService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;
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

    @Override
    public Integer addBill(BillEntity bill) {
        bill.setCreateTime(LocalDateTime.now());
        bill.setMessage(BillCategoryEnum.getMessage(bill.getCode()));
        bill.setFlag(BillCategoryEnum.getIsCost(bill.getCode()));
        bill.setUserId(8);
        int cnt = billMapper.insert(bill);
        return cnt;
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
        Page<BillEntity> billEntities = billMapper.selectPage(page,
                new LambdaQueryWrapper<BillEntity>()
                        .in(query.getCodes() != null, BillEntity::getCode, query.getCodes())
                        .le(query.getHighCost() != null, BillEntity::getCost, query.getHighCost())
                        .ge(query.getLowCost() != null, BillEntity::getCost, query.getLowCost())
                        .le(query.getEndTime() != null, BillEntity::getCostTime, query.getEndTime())
                        .ge(query.getStartTime() != null, BillEntity::getCostTime, query.getStartTime())
                        .like(StrUtil.isNotEmpty(query.getTitle()), BillEntity::getTitle, query.getTitle())
                        .orderByDesc(BillEntity::getCostTime)
                        .orderByDesc(BillEntity::getId)
        );
        return billEntities.getRecords();
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
        Map<Integer, BigDecimal> groupByCode = new HashMap<>();
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
        Map<String, Map<Boolean, BigDecimal>> groupByDate = new HashMap<>();
        for (BillEntity bill : list) {
            Map<Boolean, BigDecimal> dateGroup = groupByDate.getOrDefault(LocalDateTimeUtil.format(bill.getCostTime(), format), new HashMap<>());
            BigDecimal tmp = dateGroup.getOrDefault(bill.getFlag(), BigDecimal.ZERO);
            tmp = tmp.add(bill.getCost());
            dateGroup.put(bill.getFlag(), tmp);
            groupByDate.put(LocalDateTimeUtil.format(bill.getCostTime(), format), dateGroup);
            if (bill.getFlag()) {
                // 支出
                spend = spend.add(bill.getCost());
            } else {
                // 收入
                income = income.add(bill.getCost());
            }
            // 分类总金额
            BigDecimal sum = groupByCode.getOrDefault(bill.getCode(), BigDecimal.ZERO);
            sum = sum.add(bill.getCost());
            groupByCode.put(bill.getCode(), sum);
        }

        vo.setIncome(income);
        vo.setSpend(spend);
        vo.setBalance(income.subtract(spend));
        vo.setGroupByCode(groupByCode);
        vo.setGroupByDate(groupByDate);

        return vo;
    }
}
