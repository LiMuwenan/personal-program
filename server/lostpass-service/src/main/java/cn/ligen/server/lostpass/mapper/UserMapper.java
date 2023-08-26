package cn.ligen.server.lostpass.mapper;

import cn.ligen.server.lostpass.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ligen
 * @date 2023/8/26 13:59
 * @description
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
