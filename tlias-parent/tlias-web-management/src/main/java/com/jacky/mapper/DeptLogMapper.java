package com.jacky.mapper;

import com.jacky.pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface DeptLogMapper {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Insert("insert into dept_log(create_time, description) values(#{createTime}, #{description})")
    void insertLog(DeptLog deptLog);
}
