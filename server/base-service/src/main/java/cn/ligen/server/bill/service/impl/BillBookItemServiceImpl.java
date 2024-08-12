package cn.ligen.server.bill.service.impl;

import cn.ligen.server.bill.entity.po.BillBookItem;
import cn.ligen.server.bill.mapper.BillBookItemMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author ligen
 * @date 2024/8/12 10:40
 * @description
 */
@Service
public class BillBookItemServiceImpl extends ServiceImpl<BillBookItemMapper, BillBookItem> implements IService<BillBookItem> {
}
