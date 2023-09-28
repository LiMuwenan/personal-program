package cn.ligen.server.bill.service.impl;

import cn.ligen.server.bill.entity.BillCategory;
import cn.ligen.server.bill.mapper.BillCategoryMapper;
import cn.ligen.server.bill.service.BillCategoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ligen
 * @date 2023/9/28 21:37
 * @description
 */
@Service
public class BillCategoryServiceImpl implements BillCategoryService {

    @Resource
    private BillCategoryMapper billCategoryMapper;

    @Override
    public List<BillCategory> queryBillCategories() {
        return billCategoryMapper.selectList(null);
    }

    @Override
    public void addBillCategory(BillCategory category) {
        billCategoryMapper.insert(category);
    }

    @Override
    public void removeBillCategory(BillCategory category) {
        billCategoryMapper.deleteById(category);
    }

    @Override
    public void updateBillCategory(BillCategory category) {
        billCategoryMapper.updateById(category);
    }
}
