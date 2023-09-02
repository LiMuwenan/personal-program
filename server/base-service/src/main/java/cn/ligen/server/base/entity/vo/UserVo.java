package cn.ligen.server.base.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author ligen
 * @date 2023/8/31 22:18
 * @description
 */
@Tag(name = "展示用户信息数据")
@Data
@Accessors(chain = true)
public class UserVo {

    private Integer id;
    @Schema(description = "用户名，登录用", minLength = 5, maxLength = 10)
    private String username;
    @Schema(description = "昵称，作为名字展示", minLength = 1, maxLength = 10)
    private String nickName;
    @Schema(description = "邮箱，用户可使用的登录邮箱")
    private String email;
    @Schema(description = "手机号，用户可使用的手机号码")
    private String phone;
    @Schema(description = "头像，用户头像路径")
    private String header;
}
