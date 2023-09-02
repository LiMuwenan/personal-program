package cn.ligen.server.base.entity.mapper;

import cn.ligen.server.base.entity.UserEntity;
import cn.ligen.server.base.entity.dto.UserDto;
import cn.ligen.server.base.entity.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ligen
 * @date 2023/9/2 16:29
 * @description User类转换接口
 */
@Mapper
public interface UserEntityStruct {

    UserEntityStruct INSTANCE = Mappers.getMapper(UserEntityStruct.class);

    UserDto toDto(UserEntity userEntity);

    UserEntity toEntity(UserDto userDto);

    UserVo toVo(UserEntity userEntity);
}
