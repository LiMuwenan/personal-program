package cn.ligen.server.bill.service.impl;

import cn.ligen.server.bill.entity.BillCategory;
import cn.ligen.server.bill.entity.BillEntity;
import cn.ligen.server.bill.entity.dto.BillDto;
import cn.ligen.server.bill.mapper.BillMapper;
import cn.ligen.server.bill.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public List<BillEntity> queryBillList(BillDto dto) {
        List<BillEntity> billEntities = billMapper.selectList(null);
        return billEntities;
    }
}
