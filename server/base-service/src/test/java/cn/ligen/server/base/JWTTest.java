package cn.ligen.server.base;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.*;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.NoneJWTSigner;
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
        jwt.setHeader("alg", "HS512");
        jwt.setHeader("typ", "JWT");
        // 负载
        jwt.setPayload("username", "ligen");
        jwt.setPayload("id", 8);
        jwt.setPayload("exp", 10);


    }

    @Test
    public void parseToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpZCI6OCwidXNlcm5hbWUiOiJsaWdlbiJ9.Lqyt1-lhFVzVpgFjbpo45gG8tQ3IIA1FaS5xRSX2pAcAvs4Yi72XrtO2cAHkg6DWLVpJwtnZJaTU7uB9d5bh8Q";
        JWT jwt = JWT.of(token);

        JSONObject headers = jwt.getHeaders();
        System.out.println(headers.toString());
        JSONObject payloads = jwt.getPayloads();
        System.out.println(payloads.toString());

        System.out.println(JWTUtil.verify(token, "key".getBytes()));
    }
}
