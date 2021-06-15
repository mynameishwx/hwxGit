package com.demo.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.mapper.role_powerMapper;
import com.demo.pojo.role_power;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class role_powerImpl extends ServiceImpl<role_powerMapper, role_power> {


    @Autowired
    role_powerMapper role_powerMapper;

    @Autowired
    role_power role_power;

//    查询
    @RequiresPermissions("admin:select:*")
    public  role_power getbyid_mybatis(role_power power){
        try {
            role_power=role_powerMapper.selectById(power.getRole_powerId());
            return  role_power;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("role_power查询失败" + power.toString());
            return  role_power;
        }
    }


//    插入
    @RequiresPermissions("admin:admin:admin")
    public  Integer  insert_mybatis(role_power role_power){
        try {
            int a=role_powerMapper.insert(role_power);
            return a;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("role_power插入失败" + role_power.toString());
            return 0;
        }
    }
}
