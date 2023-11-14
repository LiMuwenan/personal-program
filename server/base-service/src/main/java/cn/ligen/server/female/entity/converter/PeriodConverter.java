package cn.ligen.server.female.entity.converter;

import cn.ligen.server.female.entity.Period;
import cn.ligen.server.female.entity.dto.PeriodDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author ligen
 * @date 2023/10/26 20:33
 * @description
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PeriodConverter {

    /**
     * dto转entity
     * @param dto
     * @return
     */
    Period toEntity(PeriodDto dto);


}
