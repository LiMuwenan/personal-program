package cn.ligen.server.bill.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author ligen
 * @date 2023/9/28 21:22
 * @description 账单种类实体
 */
@Data
@Schema(description = "账单种类实体")
@TableName("bill_category")
public class BillCategory {

    @Schema(description = "id序号")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Schema(description = "种类编号")
    private Integer code;
    @Schema(description = "种类描述")
    private String message;
    @Schema(description = "是否是支出项 1 支出 0 收入")
    private Integer status;
}
