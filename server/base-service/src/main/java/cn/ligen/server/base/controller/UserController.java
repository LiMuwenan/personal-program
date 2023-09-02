package cn.ligen.server.base.controller;

import cn.ligen.server.common.util.CommonResult;
import cn.ligen.server.base.entity.User;
import cn.ligen.server.base.entity.dto.UserDto;
import cn.ligen.server.base.entity.vo.UserVo;
import cn.ligen.server.base.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author ligen
 * @date 2023/8/26 14:17
 * @description
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "用户管理")
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public CommonResult<UserVo> userRegister(@RequestBody UserDto userDto) {
        // todo userdto==》user
        User user = new User()
                .setUsername(userDto.getUsername())
                .setNickName(userDto.getNickName())
                .setPhone(userDto.getPhone())
                .setEmail(userDto.getEmail());
        // todo 处理
        User newUser = userService.create(user, userDto.getPassword());

        // todo user==>userVo
        UserVo userVo = new UserVo()
                .setId(newUser.getId())
                .setHeader(newUser.getHeader())
                .setPhone(newUser.getPhone())
                .setUsername(newUser.getUsername())
                .setEmail(newUser.getEmail())
                .setNickName(newUser.getNickName());
        return CommonResult.success(userVo);
    }
}
