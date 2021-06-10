package com.demo.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.pojo.book;

import java.util.List;

public interface bookService extends IService<book> {

    public book getbyid_mybatis(String name);

    public  Integer insert_mybatis(book book);

    public List<book> getservicevlass(String name);
}
