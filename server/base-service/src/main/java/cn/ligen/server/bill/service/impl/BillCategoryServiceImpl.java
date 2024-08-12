package cn.ligen.server.bill.service.impl;

import cn.ligen.server.bill.entity.po.BillCategory;
import cn.ligen.server.bill.mapper.BillCategoryMapper;
import cn.ligen.server.bill.service.BillCategoryService;
import cn.ligen.server.redis.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

    @Resource
    private RedisUtil redisUtil;

    @Override
    public List<BillCategory> queryBillCategories() {
        List<BillCategory> billCategories = billCategoryMapper.selectList(
                new LambdaQueryWrapper<BillCategory>()
                        .orderByAsc(BillCategory::getCode)
        );
        // 查到的种类信息放到缓存里，方便取用
        for (BillCategory billCategory : billCategories) {
            redisUtil.set(String.valueOf(billCategory.getCode()), billCategory);
        }
        return billCategories;
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
