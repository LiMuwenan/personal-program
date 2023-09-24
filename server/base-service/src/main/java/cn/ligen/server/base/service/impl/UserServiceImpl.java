package cn.ligen.server.base.service.impl;

import cn.ligen.server.base.entity.query.UserQuery;
import cn.ligen.server.base.exception.BaseBadRequestException;
import cn.ligen.server.common.util.JWTTokenUtil;
import cn.ligen.server.common.util.UserUtil;
import cn.ligen.server.base.entity.PasswordEntity;
import cn.ligen.server.base.entity.UserEntity;
import cn.ligen.server.base.mapper.PasswordMapper;
import cn.ligen.server.base.mapper.UserMapper;
import cn.ligen.server.base.service.UserService;
import cn.ligen.server.constant.UserKeyConstant;
import cn.ligen.server.redis.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author ligen
 * @date 2023/8/26 14:42
 * @description
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordMapper passwordMapper;
    private final RedisUtil redisUtil;
    private final JWTTokenUtil tokenUtil;

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
        UserEntity one = userMapper.selectOne(
                new LambdaQueryWrapper<UserEntity>()
                        .eq(UserEntity::getUsername, userEntity.getUsername())
                        .last("limit 1")
        );
        if (one != null) {
            throw new  BaseBadRequestException("用户名已存在");
        }
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
        log.info("成功创建用户 : {}", userEntity.getId());
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
    public String checkLogin(UserEntity userEntity, String password) {
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

        // jwt token
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("username", one.getUsername());
        payloads.put("id", one.getId());
        payloads.put("email", one.getEmail());
        payloads.put("phone", one.getPhone());
        String token = tokenUtil.generatorToken(payloads, UserKeyConstant.ONLINE_TIME);

        redisUtil.set(UserKeyConstant.ONLINE_USER + token, payloads, UserKeyConstant.ONLINE_TIME, TimeUnit.HOURS);
        log.info("成功创建用户，并生成token");
        return token;
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
