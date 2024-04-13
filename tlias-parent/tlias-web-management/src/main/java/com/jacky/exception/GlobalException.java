package com.jacky.exception;

import com.jacky.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e){
        e.printStackTrace();
        System.out.println(e.getMessage());
        return Result.error("操作错误，请练习管理员");
    }
}
