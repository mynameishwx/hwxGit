package com.demo.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.Service.powerService;
import com.demo.mapper.powerMapper;
import com.demo.pojo.power;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class powerImpl extends ServiceImpl<powerMapper,power> implements powerService {


    @Autowired
    powerMapper mapper;

    @Autowired
    power power;

//    查询
    public  power  getbyid_mybatis(power power){
        try {
            power=mapper.selectById(power.getPowerId());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("power查询失败" + power.toString());
            return  power;
        }
        return  power;
    }


//    插入
    public  Integer insert_mybatis(power power){
        try {
            int x=mapper.insert(power);
            return x;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("power插入失败" + power.toString());
            return 0;
        }
    }

    @Override
    public com.demo.pojo.power getbyid_hwx(Integer id) {
        power.setPowerId(id);
        return mapper.getbyidmapper_hwx(power);
    }
}
