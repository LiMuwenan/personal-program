package cn.ligen.server.bill.controller;

import cn.ligen.server.bill.entity.BillCategory;
import cn.ligen.server.bill.service.BillCategoryService;
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
 * @author ligen
 * @date 2023/9/28 21:45
 * @description
 */
@RestController
@Tag(name = "账单种类接口", description = "有什么不同")
@RequestMapping("/api/billCategory")
public class BillCategoryController {

    @Resource
    private BillCategoryService billCategoryService;

    @Operation(summary = "添加种类")
    @PostMapping("/add")
    @Parameters(value = {
            @Parameter(name = "code", required = true),
            @Parameter(name = "message", required = true),
            @Parameter(name = "status", required = true)
    })
    public CommonResult<Object> addCategory(@RequestBody BillCategory category) {
        billCategoryService.addBillCategory(category);
        return CommonResult.success("添加成功");
    }

    @Operation(summary = "查询账单种类")
    @PostMapping("/query")
    public CommonResult<Object> queryCategoryList() {
        return CommonResult.success(billCategoryService.queryBillCategories());
    }
}
