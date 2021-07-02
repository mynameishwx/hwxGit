package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.pojo.power;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface powerMapper extends BaseMapper<power> {
    power getbyidmapper_hwx(power power);
}
