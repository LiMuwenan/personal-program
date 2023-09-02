package cn.ligen.server.base.service.impl;

import cn.ligen.server.common.util.UserUtil;
import cn.ligen.server.base.entity.PasswordEntity;
import cn.ligen.server.base.entity.UserEntity;
import cn.ligen.server.base.mapper.PasswordEntityMapper;
import cn.ligen.server.base.mapper.UserEntityMapper;
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

    private final UserEntityMapper userMapper;
    private final PasswordEntityMapper passwordMapper;

    @Value("${user.headerUrl}")
    private String headerBaseUrl;

    @Override
    public List<UserEntity> queryUserlist(UserEntity userEntity, Page<UserEntity> page) {
        return null;
    }

    @Override
    public UserEntity queryUser(UserEntity userEntity, Page<UserEntity> page) {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity create(UserEntity userEntity, String password) {
        LocalDateTime now = LocalDateTime.now();
        userEntity.setCreateTime(now);
        userEntity.setUpdateTime(now);
        // 随机头像
        userEntity.setHeader(UserUtil.genHeaderUrl(headerBaseUrl));
        userMapper.insert(userEntity);
        PasswordEntity newPass = new PasswordEntity()
                .setCreateTime(now)
                .setUpdateTime(now)
                .setUserId(userEntity.getId())
                .setPassword(password);
        passwordMapper.insert(newPass);
        return userEntity;
    }

    @Override
    public Boolean updateUser(UserEntity userEntity) {
        return null;
    }

    @Override
    public Boolean deleteUsers(Set<Integer> ids) {
        return null;
    }
}
