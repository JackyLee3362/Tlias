package com.jacky.controller;

import com.jacky.pojo.Dept;
import com.jacky.pojo.Result;
import com.jacky.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    DeptService deptService;

    @GetMapping()
    public Result getAllDept() {
        List<Dept> list = deptService.list();
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable int id) {
        Dept dept = deptService.getById(id);
        if (dept == null) {
            return Result.error("该部门不存在");
        }
        return Result.success(dept);
    }


    @DeleteMapping("/{id}")
    public Result deleteDeptById(@PathVariable int id) throws Exception {
        deptService.delete(id);
        return Result.success();
    }

    @PutMapping()
    public Result updateById(@RequestBody Dept dept) {
        deptService.updateById(dept);
        return Result.success();
    }

    @PostMapping()
    public Result insertDept(@RequestBody Dept dept) {
        String name = dept.getName();
        deptService.insert(name);
        return Result.success();

    }
}
