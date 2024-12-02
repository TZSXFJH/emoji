package com.ustclab.emoji.service.config;

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
                .addPathPatterns("/user/**")
                .addPathPatterns("/emoji/**");
    }
}
