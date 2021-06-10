package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.pojo.music;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Musicmapper extends BaseMapper<music> {

//    查询
    public List<music> getname();

//    插入
    public  Integer setname(String id);
}
