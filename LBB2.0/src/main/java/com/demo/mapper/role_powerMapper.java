package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.pojo.acc_role;
import com.demo.pojo.role_power;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface role_powerMapper extends BaseMapper<role_power> {
    List<role_power> getbyrole_hwxmapper(role_power power);
}
