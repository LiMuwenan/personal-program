package cn.ligen.server.base.service;

import cn.ligen.server.base.entity.UserEntity;
import cn.ligen.server.base.entity.query.UserQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.catalina.User;

import java.util.List;
import java.util.Set;

/**
 * @author ligen
 * @date 2023/8/26 14:39
 * @description
 */
public interface UserService {

    /**
     * 显示用户列表
     *
     * @param userEntity 传输用户数据，做查询条件
     * @param page 分页条件
     * @return 查询的用户列表
     */
    List<UserEntity> queryUserlist(UserEntity userEntity, Page<UserEntity> page);

    UserEntity queryUser(UserEntity userEntity, Page<UserEntity> page);

    /**
     * 创建一个用户
     *
     * @param userEntity 用户信息
     * @param password 用户密码, 前端私钥加密，后端公钥解密
     * @return User 创建的用户信息
     */
    UserEntity create(UserEntity userEntity, String password);

    /**
     * 更新用户数据
     *
     * @param userEntity 新的用户数据
     * @return
     */
    Boolean updateUser(UserEntity userEntity);

    Boolean deleteUsers(Set<Integer> ids);

    /**
     * 检查用户登录数据
     * @param userEntity 用户信息
     * @param password 密码信息
     * @return 判断结果
     */
    Boolean checkLogin(UserEntity userEntity, String password);

    /**
     * 查询用户，返回用户列表
     * @param query 查询条件
     * @param page 分页条件
     * @return
     */
    List<UserEntity> queryUser(UserQuery query, Page<UserEntity> page);
    UserEntity queryUser(UserQuery query);

}
