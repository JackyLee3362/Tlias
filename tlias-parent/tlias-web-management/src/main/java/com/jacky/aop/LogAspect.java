package com.jacky.aop;

import com.alibaba.fastjson.JSONObject;
import com.jacky.mapper.OperationMapper;
import com.jacky.pojo.Operation;
import com.jacky.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private OperationMapper operationMapper;


    @Around("execution(* com.jacky.service.*.*(..))")
    public Object logInfo(ProceedingJoinPoint p) throws Throwable {
        // 获得jwt
        String jwt = httpServletRequest.getHeader("token");
        Integer user = -1;
        if (StringUtils.hasLength(jwt)) {
            try {
                Claims claims = JwtUtil.parseJwt(jwt);
                user = (Integer) claims.get("id");
            } catch (Exception ignored) {
            }
        }

        long start = System.currentTimeMillis();
        Object o = p.proceed();
        long end = System.currentTimeMillis();
        String returnValue = JSONObject.toJSONString(o);
        if(returnValue.length()>2048){
            log.info("returnValue长度为{}过长，裁剪", returnValue.length());
            returnValue = returnValue.substring(0, 2048);
        }

        Operation operation = Operation.builder()
                .user(user)
                .operateTime(LocalDateTime.now())
                .className(p.getTarget().getClass().getName())
                .methodName(p.getSignature().getName())
                .returnValue(returnValue)
                .runtimeArgs(p.getArgs().toString())
                .runtimeTime(end - start)
                .build();
        operationMapper.insert(operation);
        log.info("此操作不会被记录");
        return o;

    }
}
