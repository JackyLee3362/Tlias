package com.jacky.filter;

import com.alibaba.fastjson.JSONObject;
import com.jacky.pojo.Result;
import com.jacky.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String url = req.getRequestURI();
        // 1. 如果是 /login 就放行
        //if ("/login".equals(url)) { // 我自己写的，太严格
         if(url.contains("login")){ // 放行
             log.info("用户正在登录，放行");
            filterChain.doFilter(request, response);
            return;
        }
        // 2. 判断token
        String jwt = req.getHeader("token");
         if(!StringUtils.hasLength(jwt)){
             log.info("Jwt为空，拦截");
             Result error = Result.error("NOT_LOGIN");
             String json = JSONObject.toJSONString(error);
             resp.getWriter().write(json); //获得输出流
             return;
         }

        try {
            JwtUtil.parseJwt(jwt);
        } catch (Exception e) {//jwt解析失败
            e.printStackTrace();
            log.info("解析令牌失败, 返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象--json --------> 阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        log.info("令牌合法，放行");
        filterChain.doFilter(request, response);
    }
}
