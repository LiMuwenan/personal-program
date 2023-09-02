package cn.ligen.server.common.util;

import java.util.Random;

/**
 * @author ligen
 * @date 2023/8/31 23:23
 * @description
 */
public class UserUtil {

    /**
     * 产生随机头像
     * @param baseUrl
     * @return
     */
    public static String genHeaderUrl(String baseUrl) {
        return baseUrl + (new Random().nextInt(10) + 1) + ".png";
    }
}
