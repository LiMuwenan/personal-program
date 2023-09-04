package cn.ligen.server.base.service.impl;

import cn.ligen.server.base.entity.query.UserQuery;
import cn.ligen.server.base.exception.BaseBadRequestException;
import cn.ligen.server.common.util.UserUtil;
import cn.ligen.server.base.entity.PasswordEntity;
import cn.ligen.server.base.entity.UserEntity;
import cn.ligen.server.base.mapper.PasswordMapper;
import cn.ligen.server.base.mapper.UserMapper;
import cn.ligen.server.base.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Override
    public Boolean checkLogin(UserEntity userEntity, String password) {
        UserEntity one = userMapper.selectOne(
                new LambdaQueryWrapper<UserEntity>()
                        .eq(UserEntity::getUsername, userEntity.getUsername())
        );
        if (one == null) {
            throw new BaseBadRequestException("用户名不存在");
        }
        PasswordEntity passwordEntity = passwordMapper.selectOne(
                new LambdaQueryWrapper<PasswordEntity>()
                        .eq(PasswordEntity::getUserId, one.getId())
        );
        if (!password.equals(passwordEntity.getPassword())) {
            throw new BaseBadRequestException("密码错误");
        }
        return true;
    }

    @Override
    public List<UserEntity> queryUser(UserQuery query, Page<UserEntity> page) {
        // 多查
        Page<UserEntity> userEntityPage = userMapper.selectPage(page, null);
        return userEntityPage.getRecords();
    }

    @Override
    public UserEntity queryUser(UserQuery query) {
        // 单查
        UserEntity one = userMapper.selectOne(
                new LambdaQueryWrapper<UserEntity>()
                        .eq(query.getUsername() != null, UserEntity::getUsername, query.getUsername())
                        .eq(query.getId() != null, UserEntity::getId, query.getId())
        );
        if (one == null) {
            throw new BaseBadRequestException("查找的用户不存在");
        }
        return one;
    }
}
