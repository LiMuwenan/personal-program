package cn.ligen.server.common.util;

import java.util.Map;

/**
 * @author ligen
 * @date 2023/9/24 14:12
 * @description 用户信息线程私有持有
 */
public class UserContextHolder {

    private static final ThreadLocal<Object> local = new ThreadLocal<>();

    public static void setUser(Object o) {
        local.set(o);
    }

    public static Object getUser() {
        return local.get();
    }

    public static void clear() {
        local.remove();
    }
}
