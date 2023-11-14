package cn.ligen.server.female.service;

import cn.ligen.server.female.entity.Period;
import cn.ligen.server.female.entity.dto.PeriodDto;

import java.util.List;

/**
 * @author ligen
 * @date 2023/10/24 22:49
 * @description
 */
public interface PeriodService {

    /**
     * 删除记录
     * @param period
     */
    void deleteRecord(Period period);

    /**
     * 查记录
     * @param periodDto
     * @return
     */
    List<Period> queryPeriodRecord(PeriodDto periodDto);

    /**
     * 改变某天状态
     */
    void changeDayPeriod(Period period);

}
