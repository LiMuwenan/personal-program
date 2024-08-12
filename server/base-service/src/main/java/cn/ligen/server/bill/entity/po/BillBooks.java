package cn.ligen.server.bill.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author ligen
 * @date 2024/8/12 9:40
 * @description
 */
@Data
@Schema(description = "帐本分类")
@TableName("bill_books")
public class BillBooks {

    @Schema(description = "id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "账本名称")
    private String name;
}
