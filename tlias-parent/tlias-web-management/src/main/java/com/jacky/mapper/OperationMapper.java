package com.jacky.mapper;

import com.jacky.pojo.Operation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperationMapper {

    @Insert("insert into app_log(user, operate_time, class_name, method_name, runtime_args, return_value, runtime_time) " +
            "values (#{user}, #{operateTime}, #{className}, #{methodName}, #{runtimeArgs}, #{returnValue}, #{runtimeTime})")
    void insert(Operation operation);
}
