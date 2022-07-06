package com.zjydemo.mallstore.config;

import com.zjydemo.mallstore.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zjy
 * @version 1.0
 */

/**
 * 注册拦截器
 */

@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:D:\\Work_Space\\for java\\MallStore\\upload\\");
//        registry.addResourceHandler("/images/portal/**")
//                .addResourceLocations("file:D:\\Work_Space\\for java\\MallStore\\src\\main\\resources\\static\\images\\portal\\");
    }


    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor = new LoginInterceptor();
        // 白名单集合
        // 配置白名单和黑名单
        List<String > patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/upload/**");
        patterns.add("/districts/**");
        patterns.add("/products/**");
        // 向注册器中添加拦截器
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);


    }
}
