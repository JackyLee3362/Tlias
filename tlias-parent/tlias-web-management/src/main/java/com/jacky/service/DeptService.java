package com.jacky.service;

import com.jacky.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 获得所有部门信息
     */
    List<Dept> list();

    Dept getById(int id);

    void delete(int id) throws Exception;

    void insert(String name);

    void updateById(Dept dept);
}
