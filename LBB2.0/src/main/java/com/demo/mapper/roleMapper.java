package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.pojo.role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface roleMapper extends BaseMapper<role> {
    role getbyid(Integer id);   //自定义的查询


    List<role> getbyname_hwx(role role);

}
