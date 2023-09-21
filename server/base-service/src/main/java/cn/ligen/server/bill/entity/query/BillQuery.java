package cn.ligen.server.bill.entity.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ligen
 * @date 2023/9/12 14:34
 * @description 账单查询条件类
 */
@Data
@Schema(description = "账单查询条件类")
public class BillQuery {

    @Schema(description = "模糊查询账单名称")
    private String title;

    @Schema(description = "查询开始时间 YYYY-MM-DD HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @Schema(description = "查询结束时间 YYYY-MM-DD HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @Schema(description = "查询种类范围")
    private List<Integer> codes;

    @Schema(description = "查询金额范围")
    private BigDecimal lowCost = new BigDecimal(0);
    private BigDecimal highCost = new BigDecimal(100000000);
}
