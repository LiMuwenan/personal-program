package cn.ligen.server.config;

import cn.ligen.server.interceptor.LoginInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ligen
 * @date 2023/9/24 13:26
 * @description 拦截器配置类
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns("/doc.html", "/webjars/**", "/v3/api-docs/**", "/swagger-ui.html") // knife4j, swagger
                .excludePathPatterns("/**/**.jpg", "/**/**.ico", "/**/**.png")
                .excludePathPatterns("/api/user/login", "/api/user/register");

    }
}
