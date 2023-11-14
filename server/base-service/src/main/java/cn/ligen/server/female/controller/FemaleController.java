package cn.ligen.server.female.controller;

import cn.hutool.core.date.DateUtil;
import cn.ligen.server.common.exception.BadRequestException;
import cn.ligen.server.common.util.CommonResult;
import cn.ligen.server.female.entity.Period;
import cn.ligen.server.female.entity.converter.PeriodConverter;
import cn.ligen.server.female.entity.dto.PeriodDto;
import cn.ligen.server.female.service.PeriodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
/**
 * @author ligen
 * @date 2023/10/24 21:39
 * @description 美柚
 */
@Slf4j
@RestController
@Tag(name = "例假管理")
@RequestMapping("/api/female")
public class FemaleController {

    @Resource
    private PeriodService periodService;

    @Resource
    private PeriodConverter periodConverter;

    @Operation(summary = "更新某天状态")
    @PostMapping("/change")
    @Parameters({
            @Parameter(name = "status", description = "状态1来了 2走了", required = true),
            @Parameter(name = "today", description = "日期", required = true),
            @Parameter(name = "sex", description = "是否爱爱0无 1有", required = true),
            @Parameter(name = "ovulationDay", description = "排卵日0不是 1是", required = true),
    })
    public CommonResult<Object> changeDayPeriod(@Validated @RequestBody PeriodDto periodDto) {

        periodDto.setToday(periodDto.getToday().withHour(0).withMinute(0).withSecond(0));
        // 根据时间，有记录则更新，没记录则插入
        Period entity = periodConverter.toEntity(periodDto);
        periodService.changeDayPeriod(entity);

        return CommonResult.success();
    }

    @Operation(summary = "查询日期段")
    @Parameters({
            @Parameter(name = "startTime", description = "开始日期", required = true),
            @Parameter(name = "endTime", description = "结束日期", required = true),
            @Parameter(name = "userId", description = "用户ID", required = true),
    })
    @PostMapping("/query")
    public CommonResult<Object> queryPeriod(@RequestBody PeriodDto dto) {
        return CommonResult.success(periodService.queryPeriodRecord(dto));
    }

    @Operation(summary = "设置基本信息")
    public CommonResult<Object> setProfile() {

        return CommonResult.success();
    }

}
