package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.pojo.acc_role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface acc_roleMapper extends BaseMapper<acc_role> {
    public List<acc_role> getbyuserId_hwx(acc_role role);
}
