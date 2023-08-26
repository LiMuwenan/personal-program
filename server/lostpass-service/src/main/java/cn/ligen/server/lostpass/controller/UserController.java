package cn.ligen.server.lostpass.controller;

import cn.ligen.server.common.util.CommonResult;
import cn.ligen.server.lostpass.entity.User;
import cn.ligen.server.lostpass.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ligen
 * @date 2023/8/26 14:17
 * @description
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

}
