package com.wudu.Roll.intertceptor;

import com.wudu.Roll.util.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录检查拦截器
 */
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        response.setContentType("application/json;charset=utf-8");
        // 获取请求url
        String url = request.getRequestURI();
        // 给登录接口放行
        if(url.contains("/user/login")) {
            return true;
        }
        if(url.contains("/user/register")) {
            return true;
        }
        if(token==null) {
            response.getWriter().write("{\"code\":401,\"message\":\"未登录\"}");
            return false;
        }
        try{
            Claims claims = JwtUtils.parseJwt(token);
        }catch (Exception e) {
            response.getWriter().write("{\"code\":401,\"message\":\"未登录\"}");
            return false;
        }
        return true;
    }
}
