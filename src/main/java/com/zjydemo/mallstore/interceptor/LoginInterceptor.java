package com.zjydemo.mallstore.interceptor;

import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zjy
 * @version 1.0
 */

public class LoginInterceptor implements HandlerInterceptor{



    /**
     * 检测session对象中是否有uid数据，如果有则放行，如果没有重定向到登录界面
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器(url+controller：映射)
     * @return 返回值为true表示放行
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        Object uid = request.getSession().getAttribute("uid");
        if(uid == null){
            // 为空，说明用户没有登录过，重定向到登录界面
            response.sendRedirect("/web/login.html");
            // 结束后续调用
            return false;
        }

        return true;
    }


}
