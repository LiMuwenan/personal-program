package cn.ligen.server.bill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author ligen
 * @date 2023/9/2 22:55
 * @description 账单实体类
 */
@Data
@Accessors(chain = true)
@TableName("bill_item")
public class BillEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 账目描述
     */
    private String title;
    /**
     * 账单种类编号
     */
    private Integer code;
    private BillCategory message;
    private LocalDateTime createTime;
    /**
     * 产生消费的时间
     */
    private LocalDateTime spendTime;

    private Integer userId;
    /**
     * 花费(10,2)
     */
    private BigDecimal cost;
}
