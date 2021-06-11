package com.lizhengpeng.learn.springbootlearn.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SpringBoot自定义拦截器
 * @author lzp
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    /**
     * 路径**表示任意的层级
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
    }
}
