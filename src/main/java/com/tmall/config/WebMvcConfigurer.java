package com.tmall.config;

import com.tmall.interceptor.LoginInterceptor;
import com.tmall.interceptor.OtherInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    @Bean
    public LoginInterceptor getLoginIntercepter() {
        return new LoginInterceptor();
    }

    @Bean
    public OtherInterceptor getOtherIntercepter() {
        return new OtherInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoginIntercepter()).addPathPatterns("/**");
    }
}
