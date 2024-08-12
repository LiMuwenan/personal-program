package cn.ligen.server.bill.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private String message;
    private LocalDateTime createTime;
    /**
     * 产生消费的时间
     */
    private LocalDateTime costTime;

    private Integer userId;
    /**
     * 花费(10,2)
     */
    private BigDecimal cost;

    /**
     * 支出还是收入
     * false 收入 true 支出
     * 0 收入     1    支出
     */
    private Integer flag;
}
