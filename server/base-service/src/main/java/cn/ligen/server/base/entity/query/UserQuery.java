package cn.ligen.server.base.entity.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author ligen
 * @date 2023/9/3 21:16
 * @description 用户查询器
 */
@Data
public class UserQuery {

    @Schema(description = "用户id")
    private Integer id;
    @Schema(description = "用户名")
    private String username;

}
