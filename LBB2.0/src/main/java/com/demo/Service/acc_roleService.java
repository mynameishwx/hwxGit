package com.demo.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.pojo.acc_role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface acc_roleService extends IService<acc_role> {
     public List<acc_role>  getbyname_hwx(acc_role role);
}
