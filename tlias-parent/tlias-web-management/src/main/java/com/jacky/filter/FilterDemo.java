package com.jacky.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
// import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Slf4j
// @WebFilter(urlPatterns = "/*")
// 过滤器执行的先后顺序和类名的字母顺序有关
public class FilterDemo implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化");
        // System.out.println("初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        log.info("拦截请求");
        // System.out.println("拦截请求");
    }

    @Override
    public void destroy() {
        log.info("销毁");
        // System.out.println("销毁了");
    }
}
