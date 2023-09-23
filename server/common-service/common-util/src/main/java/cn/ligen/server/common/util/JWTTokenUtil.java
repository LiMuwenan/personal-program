package cn.ligen.server.common.util;

import cn.hutool.jwt.JWTUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ligen
 * @date 2023/9/22 15:53
 * @description jwt token生成
 */
public class JWTTokenUtil {

    public static String generatorToken(Map<String, Object> headers, Map<String, Object> payloads, String key) {
        return JWTUtil.createToken(headers, payloads, key.getBytes());
    }

    public static String generatorToken(Map<String, Object> payloads, String key) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("alg", "HS256");
        headers.put("typ", "JWT");
        return JWTUtil.createToken(headers, payloads, key.getBytes());
    }

}
