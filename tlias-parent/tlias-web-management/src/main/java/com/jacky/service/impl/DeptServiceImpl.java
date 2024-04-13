package com.jacky.service.impl;

import com.jacky.mapper.DeptLogMapper;
import com.jacky.mapper.DeptMapper;
import com.jacky.mapper.EmpMapper;
import com.jacky.pojo.Dept;
import com.jacky.pojo.DeptLog;
import com.jacky.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    DeptMapper deptMapper;

    @Autowired
    EmpMapper empMapper;

    @Autowired
    DeptLogMapper deptLogMapper;

    /**
     * 获得所有部门信息
     *
     * @return
     */
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Override
    public Dept getById(int id) {
        return deptMapper.getById(id);
    }

    /**
     * 根据id删除
     */
    @Transactional // (rollbackFor = Exception.class)
    @Override
    public void delete(int id) throws Exception {
        try {
            deptMapper.delete(id);
            // int i = 1/0; // 运行时异常，会回滚
            //if(true){
            //    throw new Exception("出错了。。。"); // 不是运行时异常，不会回滚
            //}
            empMapper.deleteByDeptId(id);
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了删除操作，删除的部门是"+id);
            deptLogMapper.insertLog(deptLog);
        }
    }

    @Override
    public void insert(String name) {
        Dept dept = new Dept(null, name, LocalDateTime.now(), LocalDateTime.now());
         deptMapper.insert(dept);
    }

    @Override
    public void updateById(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
