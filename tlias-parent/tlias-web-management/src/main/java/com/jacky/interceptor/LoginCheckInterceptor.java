package com.jacky.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.jacky.pojo.Result;
import com.jacky.utils.JwtUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle( 
        @NonNull HttpServletRequest request, 
        @NonNull HttpServletResponse response, 
        @NonNull Object handler
        ) throws Exception {
        log.info("LoginCheckInterceptor.preHandle"+request.getRequestURI());
        String jwt = request.getHeader("token");
        if(!StringUtils.hasLength(jwt)){
            log.info("Jwt为空，拦截");
            Result error = Result.error("NOT_LOGIN");
            String json = JSONObject.toJSONString(error);
            response.getWriter().write(json); //获得输出流
            return false;
        }

        try {
            JwtUtil.parseJwt(jwt);
        } catch (Exception e) {//jwt解析失败
            e.printStackTrace();
            log.info("解析令牌失败, 返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象--json --------> 阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(
        @NonNull HttpServletRequest request, 
        @NonNull HttpServletResponse response, 
        @NonNull Object handler, 
        @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器：LoginCheckInterceptor.postHandle ...");
    }

    @Override
    public void afterCompletion(
        @NonNull HttpServletRequest request, 
        @NonNull HttpServletResponse response, 
        @NonNull Object handler, 
        @Nullable Exception ex) throws Exception {
        System.out.println("拦截器：LoginCheckInterceptor.afterCompletion ...");
    }
}
