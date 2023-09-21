package cn.ligen.server.bill.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author ligen
 * @date 2023/9/12 14:09
 * @description 总览页显示类
 */
@Data
@Schema(description = "数据总览实体类")
public class OverViewVo {

    @Schema(description = "总收入")
    private BigDecimal income;
    @Schema(description = "总支出")
    private BigDecimal spend;
    @Schema(description = "盈余")
    private BigDecimal balance;
    @Schema(description = "按类别金额")
    private Map<Integer, BigDecimal> groupByCode;
    @Schema(description = "按日期分组")
    Map<String, Map<Boolean, BigDecimal>> groupByDate;

}
