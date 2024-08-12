package cn.ligen.server.bill.controller;

import cn.ligen.server.bill.entity.po.BillBooks;
import cn.ligen.server.bill.service.BillBookService;
import cn.ligen.server.common.util.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ligen
 * @date: 2024/8/12 10:31
 * @description:
 */
@RestController
@Tag(name = "账本接口")
@RequestMapping("/api/billBook")
public class BillBookController {

    @Resource
    private BillBookService billBookService;

    @Operation(summary = "添加账本")
    @PostMapping("/add")
    @Parameters(value = {
            @Parameter(name = "name", required = true),
    })
    public CommonResult<Object> addBook(@RequestBody BillBooks Book) {
        billBookService.addBillBook(Book);
        return CommonResult.success("添加成功");
    }

    @Operation(summary = "查询账本")
    @PostMapping("/query")
    public CommonResult<Object> queryBookList() {
        return CommonResult.success(billBookService.queryBillBooks());
    }

    @Operation(summary = "修改账本")
    @PostMapping("/update")
    @Parameters(value = {
            @Parameter(name = "id", required = true),
            @Parameter(name = "name", required = true),
    })
    public CommonResult<Object> updateBook(@RequestBody BillBooks Book) {
        billBookService.updateBillBook(Book);
        return CommonResult.success();
    }

    @Operation(summary = "删除账本")
    @PostMapping("/delete")
    @Parameters(value = {
            @Parameter(name = "id", required = true)
    })
    public CommonResult<Object> deleteBook(@RequestBody BillBooks Book) {
        billBookService.removeBillBook(Book);
        return CommonResult.success();
    }
}
