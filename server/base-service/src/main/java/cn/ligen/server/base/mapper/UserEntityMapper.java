package cn.ligen.server.base.mapper;

import cn.ligen.server.base.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ligen
 * @date 2023/8/26 13:59
 * @description
 */
@Mapper
public interface UserEntityMapper extends BaseMapper<UserEntity> {
}
