package cn.ligen.server.lostpass.service.impl;

import cn.ligen.server.lostpass.entity.User;
import cn.ligen.server.lostpass.mapper.UserMapper;
import cn.ligen.server.lostpass.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author ligen
 * @date 2023/8/26 14:42
 * @description
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

}
