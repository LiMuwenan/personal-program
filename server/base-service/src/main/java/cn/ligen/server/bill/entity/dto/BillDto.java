package cn.ligen.server.bill.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author ligen
 * @date 2023/9/3 9:03
 * @description 账单数据传输类
 */
@Schema(description = "账单实体类")
@Data
public class BillDto {

    @Size(min = 1, max = 10, message = "主题长度在1-10字符之间", groups = {Add.class})
    @Schema(description = "账单主题，为什么花费")
    private String title;

    @Schema(description = "账单种类编号")
    @Digits(integer = 4, fraction = 0, message = "账单类型不能为空", groups = {Add.class})
    private Integer code;
    @Schema(description = "账单种类名称")
    private String message;
    @Schema(description = "账单花费时间")
    @Past(message = "设置日期只能为过去", groups = {Add.class})
    private LocalDateTime costTime;

    @Schema(description = "所属用户")
    private Integer userId;
    @Digits(integer = 12, fraction = 2, message = "账单花费不能为空", groups = {Add.class})
    @Schema(description = "账单花费金额")
    private BigDecimal cost;

    public @interface Add {}
    public @interface Query {}
    public @interface Delete {}
    public @interface Update {}
}
