package cn.ligen.server.bill.entity.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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
    private String startTime = "1970-01-01 00:00:00";
    @Schema(description = "查询结束时间 YYYY-MM-DD HH:mm:ss")
    private String endTime = "2050-12-31 00:00:00";

    @Schema(description = "查询种类范围")
    private List<Integer> codes;

    @Schema(description = "查询金额范围")
    private BigDecimal lowCost = new BigDecimal(0);
    private BigDecimal highCost = new BigDecimal(100000000);
}
