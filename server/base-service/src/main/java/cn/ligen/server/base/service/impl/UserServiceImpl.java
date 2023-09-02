package cn.ligen.server.base.service.impl;

import cn.ligen.server.common.util.UserUtil;
import cn.ligen.server.base.entity.Password;
import cn.ligen.server.base.entity.User;
import cn.ligen.server.base.mapper.PasswordMapper;
import cn.ligen.server.base.mapper.UserMapper;
import cn.ligen.server.base.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author ligen
 * @date 2023/8/26 14:42
 * @description
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordMapper passwordMapper;

    @Value("${user.headerUrl}")
    private String headerBaseUrl;

    @Override
    public List<User> queryUserlist(User user, Page<User> page) {
        return null;
    }

    @Override
    public User queryUser(User user, Page<User> page) {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User create(User user, String password) {
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        // 随机头像
        user.setHeader(UserUtil.genHeaderUrl(headerBaseUrl));
        userMapper.insert(user);
        Password newPass = new Password()
                .setCreateTime(now)
                .setUpdateTime(now)
                .setUserId(user.getId())
                .setPassword(password);
        passwordMapper.insert(newPass);
        return user;
    }

    @Override
    public Boolean updateUser(User user) {
        return null;
    }

    @Override
    public Boolean deleteUsers(Set<Integer> ids) {
        return null;
    }
}
