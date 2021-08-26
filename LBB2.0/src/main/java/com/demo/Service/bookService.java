package com.demo.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.pojo.book;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface bookService extends IService<book> {

    public book getbyid_mybatis(String name);



//    插入
    public  String  insert_hwx_book(MultipartFile file,book book);


//    模糊查询
    public  List<book>  getbook_hwx(book book);


    public  Integer insert_mybatis_s(book book);

//    信息更改
    public Integer alter_bookuser(book book);

    public List<book> getservicevlass(String name);
}
