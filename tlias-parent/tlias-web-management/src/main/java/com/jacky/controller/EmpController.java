package com.jacky.controller;

import com.jacky.pojo.Emp;
import com.jacky.pojo.PageResult;
import com.jacky.pojo.Result;
import com.jacky.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
// @CrossOrigin(value = "http://192.168.1.110:7070", maxAge = 3600)
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    EmpService empService;


    // @Deprecated
    // @GetMapping("/pageNoramlMethod")
    // public Result getEmpsByConditions(
    //         String name, Short gender,
    //         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
    //         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
    //         @RequestParam(value = "page", required = true, defaultValue = "1") int page,
    //         @RequestParam(value = "pageSize", required = true, defaultValue = "10") int pageSize) {
    //     PageResult list = empService.getByCondition(name, gender, begin, end, page, pageSize);
    //     return Result.success(list);
    // }

    @GetMapping
    public Result page(
            String name, Short gender,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
            @RequestParam(value = "page", required = true, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = true, defaultValue = "10") int pageSize) {
        PageResult list = empService.getByCondition(name, gender, begin, end, page, pageSize);
        return Result.success(list);
    }

    @DeleteMapping("/{ids}")
    public Result deleteEmpByIds(@PathVariable int[] ids) {
        empService.deleteByIds(ids);
        return Result.success();
    }

    @PostMapping
    public Result insertEmp(@RequestBody Emp emp) {
        boolean b = empService.insertEmp(emp);
        return Result.success(b);
    }

    @GetMapping("/{id}")
    public Result getEmpById(@PathVariable int id) {
        Emp emp = empService.getById(id);
        if (emp == null) return Result.error("用户不存在");
        return Result.success(emp);
    }

    @PutMapping
    public Result updateEmpByJson(@RequestBody Emp emp){
        empService.updateEmp(emp);
        log.info("更新员工");
        return Result.success();
    }
}
