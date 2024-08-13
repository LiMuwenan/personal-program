package cn.ligen.server.bill.entity.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ligen
 * @date 2024/8/12 15:15
 * @description
 */
@Data
public class BillDetailVo {

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
    /**
     * 产生消费的时间
     */
    private LocalDateTime costTime;

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

    private List<Integer> booksId;
}
