package cn.ligen.server.base.controller;

import cn.ligen.server.base.entity.mapper.UserEntityStruct;
import cn.ligen.server.base.entity.query.UserQuery;
import cn.ligen.server.base.entity.vo.TokenVo;
import cn.ligen.server.base.exception.BaseBadRequestException;
import cn.ligen.server.common.util.CommonResult;
import cn.ligen.server.base.entity.UserEntity;
import cn.ligen.server.base.entity.dto.UserDto;
import cn.ligen.server.base.entity.vo.UserVo;
import cn.ligen.server.base.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public CommonResult<UserVo> userRegister(@Validated @RequestBody UserDto userDto) {

        UserEntity user = UserEntityStruct.INSTANCE.toEntity(userDto);

        UserEntity newUser = userService.create(user, userDto.getPassword());

        UserVo userVo = UserEntityStruct.INSTANCE.toVo(newUser);

        return CommonResult.success(userVo);
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public CommonResult<TokenVo> userLogin(@Validated @RequestBody UserDto userDto) {
        UserEntity user = UserEntityStruct.INSTANCE.toEntity(userDto);

        String token = userService.checkLogin(user, userDto.getPassword());

        TokenVo tokenVo = new TokenVo();
        tokenVo.setAccessToken(token);

        return CommonResult.success("登录成功", tokenVo);
    }

    @Operation(summary = "查询用户列表", description = "分页")
    @GetMapping("/userList")
    @Parameters(value = {
            @Parameter(name = "size", description = "分页大小", required = true),
            @Parameter(name = "current", description = "当前页码", required = true)
    })
    public CommonResult<List<UserVo>> userList(UserQuery query, Page<UserEntity> page) {
        List<UserEntity> userEntities = userService.queryUser(query, page);
        List<UserVo> userVos = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            userVos.add(UserEntityStruct.INSTANCE.toVo(userEntity));
        }
        return CommonResult.success(userVos);
    }

    @Operation(summary = "查询用户", description = "单查")
    @GetMapping("/user")
    public CommonResult<UserVo> user(UserQuery query) {
        if (query.getUsername() == null && query.getId()== null) {
            throw new BaseBadRequestException("请传入用户名或用户id");
        }
        UserEntity userEntity = userService.queryUser(query);
        UserVo userVo = UserEntityStruct.INSTANCE.toVo(userEntity);
        return CommonResult.success(userVo);
    }
}
