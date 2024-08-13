package cn.ligen.server.bill.entity.mapper;

import cn.ligen.server.bill.entity.po.BillEntity;
import cn.ligen.server.bill.entity.dto.BillDto;
import cn.ligen.server.bill.entity.vo.BillDetailVo;
import cn.ligen.server.bill.entity.vo.BillVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ligen
 * @date 2023/9/3 16:24
 * @description
 */
@Mapper
public interface BillEntityStruct {

    BillEntityStruct INSTANCE = Mappers.getMapper(BillEntityStruct.class);

    BillDto toDto(BillEntity billEntity);

    BillEntity toEntity(BillDto billDto);

    BillVo toVo(BillEntity billEntity);

    BillDetailVo toDetailVo(BillEntity billEntity);

}
