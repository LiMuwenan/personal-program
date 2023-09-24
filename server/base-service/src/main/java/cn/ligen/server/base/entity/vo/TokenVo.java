package cn.ligen.server.base.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author ligen
 * @date 2023/9/24 14:40
 * @description 登录后显示给前端返回token
 */
@Schema(description = "token类")
@Data
public class TokenVo {


    @Schema(description = "通行token")
    private String accessToken;

    @Schema(description = "刷新token")
    private String refreshToken;
}
