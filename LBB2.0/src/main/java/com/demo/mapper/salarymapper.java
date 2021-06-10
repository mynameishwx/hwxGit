package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.pojo.salary;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface salarymapper extends BaseMapper<salary>  {

//    查询工资
    salary  getsalary(String id);

//    更改工资
    Integer setsalary(String name,String time,String plus,Integer salary);

//    总工资
    Integer getsalarysum();


 }
