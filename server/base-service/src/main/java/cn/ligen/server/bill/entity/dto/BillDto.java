package cn.ligen.server.bill.entity.dto;

import cn.ligen.server.bill.entity.BillCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author ligen
 * @date 2023/9/3 9:03
 * @description 账单数据传输类
 */
@Tag(name = "账单实体类")
@Data
public class BillDto {

    @NotBlank(message = "账单主题不能为空")
    @Size(min = 1, max = 10, message = "主题长度在1-10字符之间")
    @Schema(description = "账单主题，为什么花费")
    private String title;

    @Schema(description = "账单种类编号")
    private Integer code;
    @Schema(description = "账单种类名称")
    private BillCategory message;
    @Schema(description = "账单花费时间")
    private LocalDateTime spendTime;
    @NotNull(message = "账单需要所属用户")
    @Schema(description = "所属用户")
    private Integer userId;
    @NotNull(message = "账单花费不能为空")
    @Schema(description = "账单花费金额")
    private BigDecimal cost;
}
