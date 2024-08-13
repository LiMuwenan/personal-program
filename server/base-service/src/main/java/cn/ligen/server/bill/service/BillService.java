package cn.ligen.server.bill.service;

import cn.ligen.server.bill.entity.po.BillEntity;
import cn.ligen.server.bill.entity.query.BillQuery;
import cn.ligen.server.bill.entity.vo.BillDetailVo;
import cn.ligen.server.bill.entity.vo.OverViewVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Set;

/**
 * @author ligen
 * @date 2023/9/2 23:29
 * @description 账单类目
 */
public interface BillService {

    /**
     * 新增一条账单信息
     *
     * @param bill
     * @param billBooks
     * @return 插入条数
     */
    Integer addBill(BillEntity bill, Set<Integer> billBooks);

    /**
     * 导入账单信息
     * @param billList 账单列表
     * @return 插入条数
     */
    Integer importBillList(List<BillEntity> billList);

    /**
     * 获取账务列表
     * @param query
     * @return
     */
    List<BillEntity> queryBillList(BillQuery query, Page<BillEntity> page);

    /**
     * 查询账单详情
     * @param id
     * @return
     */
    BillDetailVo queryBillDetail(Integer id);

    /**
     * 统计账务总览
     * @param query
     * @return
     */
    OverViewVo billStat(BillQuery query);

    /**
     * 更新账单信息
     */
    void updateBill(BillEntity bill, Set<Integer> billBookItems);

    /**
     * 删除账单
     * @param ids
     */
    void deleteBill(List<Integer> ids);
}
