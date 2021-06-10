package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.pojo.bookmain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface bookmainmapper extends BaseMapper<bookmain> {
     public  bookmain getById(String id);

     public List<bookmain> getbysession(String session);
}
