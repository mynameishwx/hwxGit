package com.demo.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.Service.bookService;
import com.demo.mapper.bookmapper;
import com.demo.pojo.book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
public class bookimpl extends ServiceImpl<bookmapper, book> implements bookService {
       @Autowired
       bookmapper bookmapper;
       //查询
       @Override
       public  book  getbyid_mybatis(String name){
           return  bookmapper.selectById(name);
       }

       //插入
       @Override
       public  Integer insert_mybatis(book book){
           return  bookmapper.insert(book);
       }

    @Override
    public List<book> getservicevlass(String name) {
       return  bookmapper.getbookclass(name);
    }
    //删除

       public  Integer delete_bookuserMybatis(String name){
           return  bookmapper.deleteById(name);
     }

    //修改
    public  Integer alterbookuser(book book){
           return  bookmapper.updateById(book);
    }
}
