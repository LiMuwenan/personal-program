package cn.ligen.server.bill.controller;

import cn.ligen.server.bill.entity.BillCategory;
import cn.ligen.server.bill.entity.BillEntity;
import cn.ligen.server.bill.entity.dto.BillDto;
import cn.ligen.server.bill.entity.mapper.BillEntityStruct;
import cn.ligen.server.bill.entity.vo.BillVo;
import cn.ligen.server.bill.service.BillService;
import cn.ligen.server.common.util.CommonResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ligen
 * @date 2023/9/2 23:32
 * @description
 */
@RestController
@Tag(name = "账单管理")
@RequestMapping("/api/bill")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;
    @PostMapping("/add")
    public CommonResult<Object> addBill(@Validated @RequestBody BillDto dto) {
        BillEntity entity = BillEntityStruct.INSTANCE.toEntity(dto);
        Integer cnt = billService.addBill(entity);
        return CommonResult.success(cnt);
    }

    @PostMapping("/import")
    public CommonResult<Object> importBills(@Validated @RequestBody List<BillDto> dto) {
        List<BillEntity> billList = new ArrayList<>(dto.size());
        for (BillDto billDto : dto) {
            billList.add(BillEntityStruct.INSTANCE.toEntity(billDto));
        }
        Integer cnt = billService.importBillList(billList);
        return CommonResult.success(cnt);
    }

    @GetMapping("/list")
    public CommonResult<List<BillVo>> billList(BillDto dto) {
        List<BillEntity> billEntities = billService.queryBillList(dto);
        List<BillVo> billVos = new ArrayList<>();
        for (BillEntity billEntity : billEntities) {
            billVos.add(BillEntityStruct.INSTANCE.toVo(billEntity));
        }
        return CommonResult.success(billVos);
    }
}
