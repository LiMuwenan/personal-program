package cn.ligen.server.base.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * @author ligen
 * @date 2023/8/31 22:18
 * @description
 */
@Tag(name = "用户类传输数据")
@Data
@Accessors(chain = true)
public class UserDto {

    @Schema(description = "用户名，登录用", minLength = 5, maxLength = 10)
    @Length(min = 5, max = 10, message = "用户名长度5-10字符")
    @NotNull(message = "用户名不能为空")
    private String username;
    @Schema(description = "昵称，作为名字展示", minLength = 1, maxLength = 10)
    @Length(min = 1, max = 10, message = "昵称长度1-10字符")
    @NotNull(message = "昵称不能为空")
    private String nickName;
    @Schema(description = "邮箱，用户可使用的登录邮箱")
    private String email;
    @Schema(description = "手机号，用户可使用的手机号码")
    private String phone;
    @Schema(description = "用户密码", type = "password")
    private String password;
}
