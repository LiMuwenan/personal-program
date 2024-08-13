package cn.ligen.server.bill.mapper;

import cn.ligen.server.bill.entity.po.BillBookItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

/**
 * @author ligen
 * @date 2024/8/12 9:54
 * @description
 */
@Mapper
public interface BillBookItemMapper extends BaseMapper<BillBookItem> {

    void saveBatch(Collection<BillBookItem> billBookItem);
}
