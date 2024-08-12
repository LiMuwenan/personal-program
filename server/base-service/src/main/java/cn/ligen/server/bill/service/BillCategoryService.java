package cn.ligen.server.bill.service;

import cn.ligen.server.bill.entity.po.BillCategory;

import java.util.List;

/**
 * @author ligen
 * @date 2023/9/28 21:32
 * @description
 */
public interface BillCategoryService {

    public List<BillCategory> queryBillCategories();

    /**
     * 添加一个种类
     */
    public void addBillCategory(BillCategory category);

    /**
     * 删除一个种类
     */
    public void removeBillCategory(BillCategory category);

    /**
     * 更新一个种类
     */
    public void updateBillCategory(BillCategory category);
}
