package cn.ligen.server.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.ligen.server.base.entity.UserEntity;
import cn.ligen.server.common.exception.BadRequestException;
import cn.ligen.server.common.util.JWTTokenUtil;
import cn.ligen.server.common.util.UserContextHolder;
import cn.ligen.server.constant.UserKeyConstant;
import cn.ligen.server.redis.RedisUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @author ligen
 * @date 2023/9/24 13:19
 * @description 登录拦截，验证token
 */
@Slf4j
@Configuration
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private JWTTokenUtil tokenUtil;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("authorization");
        if (StrUtil.isBlank(token)) {
            log.error("未携带token，禁止访问");
            throw new BadRequestException("未携带token，禁止访问");
        }
        token = token.replace("Bearer ", "").replace("bearer ", "");
        if (!tokenUtil.validate(token)) {
            log.error("token解析失败");
            return false;
        }
        Map<String, Object> payloads = (Map<String, Object>) redisUtil.get(UserKeyConstant.ONLINE_USER + token);
        UserEntity userEntity = new UserEntity();
        userEntity.setId((Integer) payloads.get("id"));
        userEntity.setUsername((String) payloads.get("username"));
        userEntity.setPhone((String) payloads.get("phone"));
        userEntity.setEmail((String) payloads.get("email"));
        UserContextHolder.setUser(payloads);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        UserContextHolder.clear();
        log.info("请求完成，清理线程私有变量");
    }
}
