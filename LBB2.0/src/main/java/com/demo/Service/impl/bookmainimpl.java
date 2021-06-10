package com.demo.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.Service.bookmainService;
import com.demo.mapper.bookmainmapper;
import com.demo.pojo.bookmain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
public class bookmainimpl extends ServiceImpl<bookmainmapper, bookmain> implements bookmainService {
      @Autowired
     bookmainmapper bookmainmapper;

      //mybatis 查询
      @Override
      public bookmain  mybatis_getmajor(String id){
          return  bookmainmapper.getById(id);
//          return  bookmainmapper.selectById(id);
      }

    @Override
    public List<bookmain> getbysessions(String session) {
          return bookmainmapper.getbysession(session);

    }
//插入

    @Override
    public Integer insert_major(bookmain bookmain) {

        return bookmainmapper.insert(bookmain);
    }
}
