package com.jacky.controller;

import com.jacky.pojo.Emp;
import com.jacky.pojo.Result;
import com.jacky.service.EmpService;
import com.jacky.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    EmpService empService;
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        Emp e = empService.login(emp);
        if(e != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", emp.getId());
            claims.put("name", emp.getName());
            claims.put("username", emp.getUsername());
            String s = JwtUtil.generateJwt(claims);
            return Result.success(s);
        }
        return Result.error("用户名或密码错误");
    }
}
