package cn.ligen.server.bill.service;

import cn.ligen.server.bill.entity.BillEntity;

import java.util.List;

/**
 * @author ligen
 * @date 2023/9/2 23:29
 * @description 账单类目
 */
public interface BillService {

    /**
     * 新增一条账单信息
     * @param bill
     * @return 插入条数
     */
    Integer addBill(BillEntity bill);

    /**
     * 导入账单信息
     * @param billList 账单列表
     * @return 插入条数
     */
    Integer importBillList(List<BillEntity> billList);
}
