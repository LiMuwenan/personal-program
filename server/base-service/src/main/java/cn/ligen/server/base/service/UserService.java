package cn.ligen.server.base.service;

import cn.ligen.server.base.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

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
     * @param user 传输用户数据，做查询条件
     * @param page 分页条件
     * @return 查询的用户列表
     */
    List<User> queryUserlist(User user, Page<User> page);

    User queryUser(User user, Page<User> page);

    /**
     * 创建一个用户
     *
     * @param user 用户信息
     * @param password 用户密码, 前端私钥加密，后端公钥解密
     * @return User 创建的用户信息
     */
    User create(User user, String password);

    /**
     * 更新用户数据
     *
     * @param user 新的用户数据
     * @return
     */
    Boolean updateUser(User user);

    Boolean deleteUsers(Set<Integer> ids);

}
