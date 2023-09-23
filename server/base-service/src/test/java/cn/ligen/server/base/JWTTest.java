package cn.ligen.server.base;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ligen
 * @date 2023/9/22 15:42
 * @description hutool jwt
 */
@SpringBootTest
public class JWTTest {

    @Test
    public void generateToken() {
        JWT jwt = new JWT();
        // 头
        Map<String, Object> headers = new HashMap<>();
        headers.put("alg", "HS512");
        headers.put("typ", "JWT");
        jwt.addHeaders(headers);

        // 负载
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("username", "ligen");
        payloads.put("id", 8);
        jwt.addPayloads(payloads);

        String token = JWTUtil.createToken(headers, payloads, "key".getBytes());

        System.out.println(token);
    }
}
