package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.pojo.acc_role;
import com.demo.pojo.role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface acc_roleMapper extends BaseMapper<acc_role> {

    public List<acc_role> getbyuserId_hwx(acc_role role);

   Integer insert_hwx(acc_role acc_role);

   Integer updata_hwx(role role);
}
