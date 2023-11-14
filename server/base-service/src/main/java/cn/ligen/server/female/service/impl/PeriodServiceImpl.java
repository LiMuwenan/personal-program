package cn.ligen.server.female.service.impl;

import cn.ligen.server.common.util.UserContextHolder;
import cn.ligen.server.female.entity.Period;
import cn.ligen.server.female.entity.dto.PeriodDto;
import cn.ligen.server.female.mapper.PeriodMapper;
import cn.ligen.server.female.service.PeriodService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author ligen
 * @date 2023/10/24 22:50
 * @description
 */
@Service
public class PeriodServiceImpl implements PeriodService {

    @Resource
    private PeriodMapper periodMapper;

    @Override
    public void deleteRecord(Period period) {

    }

    @Override
    public List<Period> queryPeriodRecord(PeriodDto periodDto) {
        Integer userId = (Integer) ((HashMap<String, Object>) UserContextHolder.getUser()).get("id");
        List<Period> periods = periodMapper.selectList(
                new LambdaQueryWrapper<Period>()
                        .eq(Period::getUserId, userId)
                        .le(Period::getToday, periodDto.getEndTime())
                        .ge(Period::getToday, periodDto.getStartTime())
        );
        return periods;
    }

    @Override
    public void changeDayPeriod(Period period) {
        // 新增或改变一天的状态
        Integer userId = (Integer) ((HashMap<String, Object>) UserContextHolder.getUser()).get("id");
        Period one = periodMapper.selectOne(
                new LambdaQueryWrapper<Period>()
                        .eq(Period::getToday, period.getToday())
                        .eq(Period::getUserId, userId)
        );
        if (Objects.isNull(one)) {
            period.setUserId(userId);
            periodMapper.insert(period);
        } else {
            period.setId(one.getId());
            periodMapper.updateById(period);
        }

        // todo 如果改变了姨妈事件，预计排卵日，插入或更新记录
    }
}
