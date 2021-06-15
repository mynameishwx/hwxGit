package com.demo.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.Service.roleService;
import com.demo.mapper.roleMapper;
import com.demo.pojo.role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class roleImpl extends ServiceImpl<roleMapper,role> implements roleService {
    @Autowired
    roleMapper roleMapper;

    @Autowired
    role role;

//    查询
    public  role   getbyid_mybatis(role role){
        try {
             role=roleMapper.selectById(role.getRoleId());
             return  role;
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("role查询失败" + role.toString());
            return  role;
        }
    }

//    插入
    public  Integer  insert_mybatis(role role){
        try {
            int x=roleMapper.insert(role);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("role插入失败" + role.toString());
            return 0;
        }
        return  1;
    }
}
