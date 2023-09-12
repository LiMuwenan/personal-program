package cn.ligen.server.bill.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.ligen.server.bill.entity.BillCategoryEnum;
import cn.ligen.server.bill.entity.BillEntity;
import cn.ligen.server.bill.entity.query.BillQuery;
import cn.ligen.server.bill.mapper.BillMapper;
import cn.ligen.server.bill.service.BillService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        bill.setUserId(8);
        bill.setCostTime(bill.getCostTime().plusHours(20L));
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
                        .orderByDesc(BillEntity::getCostTime, BillEntity::getId)
        );
        return billEntities.getRecords();
    }
}
