package cn.ligen.server.common.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.ligen.server.common.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ligen
 * @date 2023/9/22 15:53
 * @description jwt token生成
 */
@Component
public class JWTTokenUtil {

    @Value("${token.key}")
    private String tokenKey;

    public String generatorToken(Map<String, Object> headers, Map<String, Object> payloads) {
        return JWTUtil.createToken(headers, payloads, tokenKey.getBytes());
    }

    /**
     * 生成JWT token，并设置过期时间，单位小时
     * @param payloads 负载
     * @param time 过期时间，单位：小时
     * @return
     */
    public String generatorToken(Map<String, Object> payloads, Long time) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("alg", "HS256");
        headers.put("typ", "JWT");

        Date now = new Date();
        // 签发时间
        payloads.put(JWT.ISSUER, now);
        // 过期时间
        payloads.put(JWT.EXPIRES_AT, DateUtil.offsetHour(now, time.intValue()));
        // 生效时间
        payloads.put(JWT.NOT_BEFORE, now);
        return JWTUtil.createToken(headers, payloads, tokenKey.getBytes());
    }

    /**
     * 验证 token
     * @param token
     * @return
     */
    public Boolean validate(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        if (!jwt.setKey(tokenKey.getBytes()).verify()) {
            throw new BadRequestException("token验证异常");
        }
        if (!jwt.validate(0)) {
            throw new BadRequestException("token验证过期");
        }
        return true;
    }

    /**
     * 获取负载信息
     * @param token
     * @return
     */
    public Map<String, Object> getPayloads(String token) {
        return JWTUtil.parseToken(token).getPayloads();
    }

}
