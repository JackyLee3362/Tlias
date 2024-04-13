package com.jacky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jacky.mapper.EmpMapper;
import com.jacky.pojo.Emp;
import com.jacky.pojo.PageResult;
import com.jacky.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    EmpMapper empMapper;

    @Override
    public List<Emp> list() {
        return empMapper.list();
    }

    @Override
    public Emp getById(int id) {
        return empMapper.getById(id);
    }

    @Override
    public PageResult getByCondition(String name, Short gender, LocalDate begin, LocalDate end,
                                     int page,
                                     int pageSize) {
        log.info(name + " " + gender + " " + begin + " " + end);
        PageHelper.startPage(page, pageSize);

        List<Emp> emps = empMapper.getByCondition(name, gender, begin, end, (page - 1) * pageSize, pageSize);
        Page<Emp> p = (Page<Emp>) emps;
        PageResult pageResult = new PageResult(p.getTotal(), p.getResult());
        p.close(); // 为什么要 close
        return pageResult;

    }
    @Override
    public void deleteByIds(int[] ids) {
        empMapper.deleteByIds(ids);
    }

    @Override
    public boolean insertEmp(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        if (emp.getPassword() == null) {
            emp.setPassword("123456");
        }
        return empMapper.insert(emp);
    }

    @Override
    public void updateEmp(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getEmpByUsernameAndPassword(emp);
    }

}
