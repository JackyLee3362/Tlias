package com.jacky.controller;

import com.jacky.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class SessionController {

    @GetMapping("/c1")
    public Result c1(HttpServletResponse response){
        response.addCookie(new Cookie("login_name", "jacky"));
        return Result.success();
    }

    @GetMapping("/c2")
    public Result c2(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("login_name")){
                log.info("login username " + cookie.getValue());
            }
        }
        return Result.success();
    }
    @GetMapping("/s1")
    public Result s1(HttpSession session){
        session.setAttribute("login_session", "jacky-session");
        log.info("session hash: {}", session.hashCode());
        return Result.success();
    }

    @GetMapping("/s2")
    public Result s2(HttpServletRequest request){
        HttpSession session = request.getSession();
        log.info("session hash: {}", session.hashCode());
        Object loginSession = session.getAttribute("login_session");
        log.info("login_session, {}", loginSession);
        return Result.success();
    }



}
