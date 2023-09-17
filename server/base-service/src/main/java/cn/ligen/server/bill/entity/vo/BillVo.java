package cn.ligen.server.bill.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author ligen
 * @date 2023/9/3 16:25
 * @description 账单显示类
 */
@Data
public class BillVo {
    @Schema(description = "账单种类名称")
    private String message;
    @Schema(description = "账单种类编号")
    private Integer code;
    @Schema(description = "账单花费时间 yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime costTime;
    @Schema(description = "账单主题，为什么花费")
    private String title;
    @Schema(description = "账单花费金额")
    private BigDecimal cost;
}
