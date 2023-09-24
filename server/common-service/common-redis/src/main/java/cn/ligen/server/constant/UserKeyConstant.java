package cn.ligen.server.constant;

/**
 * @author ligen
 * @date 2023/9/23 15:53
 * @description
 */
public interface UserKeyConstant {

    /**
     * 在线用户key，value为token
     */
    String ONLINE_USER = "online-user:";

    /**
     * 一次授权，12小时
     */
    Long ONLINE_TIME = 12L;
}
