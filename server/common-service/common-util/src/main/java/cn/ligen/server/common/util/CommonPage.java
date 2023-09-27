package cn.ligen.server.common.util;

import lombok.Data;

import java.util.List;

/**
 * @author ligen
 * @date 2023/9/12 16:42
 * @description 统一分页返回类
 */
@Data
public class CommonPage<T> {

    /**
     * 当前页码
     */
    private Long current;
    /**
     * 分页大小（本次查询到数据量）
     */
    private Long size;
    /**
     * 符合条件的总数据量，不管分页
     */
    private Long total;
    /**
     * 结果集
     */
    private List<T> records;
}
