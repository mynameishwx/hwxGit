package com.demo.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.pojo.acc_book;

public interface acc_bookService extends IService<acc_book> {

//    查询
    public  acc_book   getacc_book_mybatis(String acc_book_id);

//    插入
    public  Integer  insert_mybatis(acc_book book);

//    删除
    public  Integer delete_mybatis(acc_book book);

//    更改
    public  Integer alter_hwx(acc_book acc_book);

//    插入(hwx)
    public  Integer insert_hwx(acc_book acc_book);
}
