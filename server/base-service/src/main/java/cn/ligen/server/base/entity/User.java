package cn.ligen.server.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author ligen
 * @date 2023/8/26 13:10
 * @description
 */
@Data
@Accessors(chain = true)
@TableName("sys_users")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String username;
    private String nickName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    /**
     * 0-未激活 1-正常 2-删除
     */
    private Integer status;
    private String email;
    private String phone;
    /**
     * 头像路径
     */
    private String header;
}
