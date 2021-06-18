package com.demo.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.pojo.role;

import java.util.List;

public interface roleService extends IService<role> {
   public List<role> getbyname_hwx(role role);


   public  role  getByid_mybatis(Integer id);
}
