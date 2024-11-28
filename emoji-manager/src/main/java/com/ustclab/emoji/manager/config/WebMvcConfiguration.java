package com.ustclab.emoji.manager.config;

import com.ustclab.emoji.common.interceptor.LoginAuthInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author TZSXFJH
 * @date 2024/11/27
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Resource
    private LoginAuthInterceptor loginAuthInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginAuthInterceptor)
                .excludePathPatterns("/admin/system/index/login",
                        "/admin/system/index/register")
                .addPathPatterns("/**");
    }
}
