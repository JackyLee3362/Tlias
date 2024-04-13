package com.jacky.service;

import com.jacky.pojo.Emp;
import com.jacky.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    List<Emp> list();

    Emp getById(int id);

    PageResult getByCondition(String name, Short gender, LocalDate begin, LocalDate end, int page, int pageSize);

    void deleteByIds(int[] ids);

    boolean insertEmp(Emp emp);
    void updateEmp(Emp emp);


    Emp login(Emp emp);
}
