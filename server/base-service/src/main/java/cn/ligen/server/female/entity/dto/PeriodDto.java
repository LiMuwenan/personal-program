package cn.ligen.server.female.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;

/**
 * @author ligen
 * @date 2023/10/24 22:52
 * @description
 */
@Data
public class PeriodDto {

    @Schema(description = "id")
    private Integer id;
    @Schema(description = "0 无记录 1 来了 2走了")
    @Max(value = 2, message = "状态值0，1，2")
    @Min(value = 0, message = "状态值0，1，2")
    private Integer status;
    @Schema(description = "记录时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime today;
    @Schema(description = "产生记录的用户ID")
    private Integer userId;
    @Schema(description = "是否爱爱，默认0否，1是")
    @Max(value = 1, message = "状态值0，1，2")
    @Min(value = 0, message = "状态值0，1，2")
    private Integer sex;
    @Schema(description = "是否排卵日，默认0否，1是")
    @Max(value = 1, message = "状态值0，1，2")
    @Min(value = 0, message = "状态值0，1，2")
    private Integer ovulationDay;
    @Schema(description = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime startTime;
    @Schema(description = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime endTime;

}
