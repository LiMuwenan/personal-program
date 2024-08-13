package cn.ligen.server.bill.service.impl;

import cn.ligen.server.base.exception.BaseBadRequestException;
import cn.ligen.server.bill.entity.po.BillBookItem;
import cn.ligen.server.bill.entity.po.BillBooks;
import cn.ligen.server.bill.mapper.BillBookItemMapper;
import cn.ligen.server.bill.mapper.BillBooksMapper;
import cn.ligen.server.bill.service.BillBookService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ligen
 * @date 2024/8/12 10:38
 * @description
 */
@Service
public class BillBookServiceImpl extends ServiceImpl<BillBooksMapper, BillBooks> implements BillBookService {

    @Resource
    private BillBooksMapper billBooksMapper;
    @Resource
    private BillBookItemMapper billBookItemMapper;

    @Override
    public List<BillBooks> queryBillBooks() {
        return billBooksMapper.selectList(new LambdaQueryWrapper<BillBooks>().orderByAsc(BillBooks::getCode));
    }

    @Override
    public void addBillBook(BillBooks books) {
        billBooksMapper.insert(books);
    }

    @Override
    public void removeBillBook(BillBooks books) {
        BillBookItem billBookItem = billBookItemMapper.selectOne(new LambdaQueryWrapper<BillBookItem>().eq(BillBookItem::getBookId, books.getId()).last("limit 1"));
        if (billBookItem != null) {
            throw new BaseBadRequestException("账本中有账单");
        }
        billBooksMapper.deleteById(books.getId());
    }

    @Override
    @Transactional
    public void updateBillBook(BillBooks books) {
        billBooksMapper.updateById(books);
    }
}
