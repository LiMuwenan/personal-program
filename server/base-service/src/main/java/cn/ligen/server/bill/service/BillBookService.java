package cn.ligen.server.bill.service;

import cn.ligen.server.bill.entity.po.BillBooks;
import cn.ligen.server.bill.entity.po.BillCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author: ligen
 * @date: 2024/8/12 10:37
 * @description:
 */
public interface BillBookService extends IService<BillBooks> {

    public List<BillBooks> queryBillBooks();

    /**
     * 添加一个账本
     */
    public void addBillBook(BillBooks books);

    /**
     * 删除一个账本
     */
    public void removeBillBook(BillBooks books);

    /**
     * 更新一个账本
     */
    public void updateBillBook(BillBooks books);
}
