package cn.ligen.server.female.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ligen
 * @date 2023/10/24 22:19
 * @description 日期记录
 */
@Data
@Schema(description = "例假记录实体类")
@TableName("female_period")
public class Period {

    @TableId(type = IdType.AUTO)
    @Schema(description = "id")
    private Integer id;
    @Schema(description = "0 无记录 1 来了 2走了")
    private Integer status;
    @Schema(description = "记录时间")
    private LocalDateTime today;
    @Schema(description = "产生记录的用户ID")
    private Integer userId;
    @Schema(description = "是否爱爱，默认0否，1是")
    private Integer sex;
    @Schema(description = "是否排卵日，默认0否，1是")
    private Integer ovulationDay;
//    @Schema(description = "持续天数")
//    private Integer period;
}
