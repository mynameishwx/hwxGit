package com.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface wxmapper {
    Integer setdata(String dname,String ddata,String dtime,String dqun);
}
