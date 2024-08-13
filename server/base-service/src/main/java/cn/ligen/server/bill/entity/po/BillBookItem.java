package cn.ligen.server.bill.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author ligen
 * @date 2024/8/12 9:47
 * @description
 */
@Data
@Schema(description = "账本账单关系表")
@TableName("bill_book_item")
public class BillBookItem {

    @Schema(description = "账本id")
    private Integer bookId;

    @Schema(description = "账单id")
    private Integer itemId;

    public BillBookItem(Integer bookId, Integer itemId) {
        this.bookId = bookId;
        this.itemId = itemId;
    }
}
