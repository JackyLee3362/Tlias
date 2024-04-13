package com.jacky.mapper;

import com.jacky.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from dept")
    List<Dept> list();

    @Select("select * from dept where id = #{id}")
    Dept getById(int id);


    @Insert("insert into dept(name, create_time, update_time)" +
            "values (#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    @Delete("delete from dept where id = #{id}")
    void delete(int id);

    @Update("update dept set name=#{name} ,update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);



}
