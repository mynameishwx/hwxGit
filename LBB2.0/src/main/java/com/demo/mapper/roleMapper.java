package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.pojo.role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface roleMapper extends BaseMapper<role> {
//    role getbyid(role role);   //自定义的查询

}
