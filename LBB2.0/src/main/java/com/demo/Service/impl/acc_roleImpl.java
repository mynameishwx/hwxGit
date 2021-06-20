package com.demo.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.Service.acc_roleService;
import com.demo.mapper.acc_roleMapper;
import com.demo.pojo.acc_role;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class acc_roleImpl extends ServiceImpl<acc_roleMapper, acc_role> implements acc_roleService {
    @Autowired
    acc_roleMapper roleMapper;

    @Autowired
    acc_role acc_role;

    @Autowired
    acc_roleMapper acc_roleMapper;




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

    @Override
    public Integer insert_hwx(com.demo.pojo.acc_role acc_role) {
        try {
            roleMapper.insert_hwx(acc_role);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("acc_role插入失败" + acc_role.toString());
            return 0;
        }
        return 1;
    }

    @Override
    public List<acc_role> getbyname_hwx(acc_role role) {
        return acc_roleMapper.getbyuserId_hwx(role);
    }

    @Override
    public Integer updata_hwx(com.demo.pojo.acc_role role) {
        try {
            int y=roleMapper.updateById(role);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("acc_role更新失败" + acc_role.toString());
            return 0;
        }
        return 1;
    }
}
