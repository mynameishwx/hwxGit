package com.demo.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.pojo.role_power;

import java.util.List;

public interface role_powerService extends IService<role_power> {

    List<role_power>  getbyrole_hwx(role_power role_power);


}
