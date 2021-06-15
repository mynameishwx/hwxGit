package com.demo.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.mapper.acc_roleMapper;
import com.demo.pojo.acc_role;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class acc_roleImpl extends ServiceImpl<acc_roleMapper, acc_role> {
    @Autowired
    acc_roleMapper roleMapper;

    @Autowired
    acc_role acc_role;
//  查询
    @RequiresPermissions("admin:select:*")
    public  acc_role  getbyid_mybatis(acc_role role){
        try {
            acc_role=roleMapper.selectById(role.getAcc_roleId());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("acc_role查询失败" + acc_role.toString());
            return acc_role;
        }
        return  acc_role;
    }


//    插入
    @RequiresPermissions("admin:updata:*")
    public  Integer  insert_mybatis(acc_role acc_role){
        try {
            int y=roleMapper.insert(acc_role);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("acc_role插入失败" + acc_role.toString());
            return 0;
        }
        return 1;
    }
}
