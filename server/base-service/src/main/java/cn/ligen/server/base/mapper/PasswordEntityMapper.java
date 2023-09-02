package cn.ligen.server.base.mapper;

import cn.ligen.server.base.entity.PasswordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ligen
 * @date 2023/8/31 23:33
 * @description 操作密码实体类
 */
@Mapper
public interface PasswordEntityMapper extends BaseMapper<PasswordEntity> {
}
