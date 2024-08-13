package cn.ligen.server.bill.mapper;

import cn.ligen.server.bill.entity.po.BillEntity;
import cn.ligen.server.bill.entity.query.BillQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ligen
 * @date 2023/9/2 23:28
 * @description
 */
@Mapper
public interface BillMapper extends BaseMapper<BillEntity> {

    /**
     * 分页查询
     * @param userId
     * @param query
     * @param offset
     * @param limit
     * @return
     */
    List<BillEntity> selectPage(Integer userId, BillQuery query, Long offset, Long limit);

    /**
     * 分页查询总数
     * @param userId
     * @param query
     * @return
     */
    Long selectPageCount(Integer userId, BillQuery query);

    /**
     * 查询列表
     *
     * @param userId
     * @param query
     * @return
     */
    List<BillEntity> selectList(Integer userId, BillQuery query);
}
