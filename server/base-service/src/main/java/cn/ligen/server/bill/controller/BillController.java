package cn.ligen.server.bill.controller;

import cn.ligen.server.bill.entity.BillEntity;
import cn.ligen.server.bill.entity.dto.BillDto;
import cn.ligen.server.bill.entity.mapper.BillEntityStruct;
import cn.ligen.server.bill.entity.vo.BillVo;
import cn.ligen.server.bill.service.BillService;
import cn.ligen.server.common.util.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ligen
 * @date 2023/9/2 23:32
 * @description
 */
@Slf4j
@RestController
@Tag(name = "账单管理")
@RequestMapping("/api/bill")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @Operation(summary = "添加账单信息")
    @PostMapping("/add")
    @Parameters({
            @Parameter(name = "title", description = "账单名称", required = true),
            @Parameter(name = "code", description = "账单类型", required = true),
            @Parameter(name = "costTime", description = "账单花费时间", required = true),
            @Parameter(name = "cost", description = "账单花费金额", required = true)
    })
    public CommonResult<Object> addBill(@Validated({BillDto.Add.class}) @RequestBody BillDto dto) {
        BillEntity entity = BillEntityStruct.INSTANCE.toEntity(dto);
        Integer cnt = billService.addBill(entity);
        return CommonResult.success(null);
    }

    @Operation(summary = "批量导入账单信息")
    @PostMapping("/import")
    @Parameters({
            @Parameter(name = "title", description = "账单名称", required = true),
            @Parameter(name = "code", description = "账单类型", required = true),
            @Parameter(name = "costTime", description = "账单花费时间", required = true),
            @Parameter(name = "cost", description = "账单花费金额", required = true)
    })
    public CommonResult<Object> importBills(@Validated({BillDto.Add.class}) @RequestBody List<BillDto> dto) {
        List<BillEntity> billList = new ArrayList<>(dto.size());
        for (BillDto billDto : dto) {
            billList.add(BillEntityStruct.INSTANCE.toEntity(billDto));
        }
        Integer cnt = billService.importBillList(billList);
        return CommonResult.success(cnt);
    }

    @Operation(summary = "获取账单列表")
    @GetMapping("/list")
    @Parameters({
            @Parameter(name = "userId", description = "用户ID", required = true)
    })
    public CommonResult<List<BillVo>> billList(BillDto dto) {
        List<BillEntity> billEntities = billService.queryBillList(dto);
        List<BillVo> billVos = new ArrayList<>();
        for (BillEntity billEntity : billEntities) {
            billVos.add(BillEntityStruct.INSTANCE.toVo(billEntity));
        }
        return CommonResult.success(billVos);
    }
}
