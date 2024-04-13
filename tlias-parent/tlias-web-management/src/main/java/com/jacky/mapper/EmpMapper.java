package com.jacky.mapper;

import com.jacky.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    @Select("select * from emp")
    List<Emp> list();
    @Select("select * from emp where id = #{id}")
    Emp getById(int id);

    List<Emp> getByCondition(String name, Short gender, LocalDate begin, LocalDate end, int start, int pageSize);

    void deleteByIds(int[] ids);

    @Insert("insert into emp(username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username}, #{password}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    boolean insert(Emp emp);

    void update(Emp emp);

    @Select("select count(*) from emp")
    Long count();

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getEmpByUsernameAndPassword(Emp emp);

    @Delete("delete from emp where dept_id = #{id}")
    void deleteByDeptId(int id);
}
