package cn.ligen.server.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author ligen
 * @date 2023/8/31 23:31
 * @description
 */
@Data
@Accessors(chain = true)
@TableName("sys_pass")
public class Password {

    @TableId
    private Integer userId;
    private String password;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
