package com.demo.Service;

import org.springframework.ui.Model;

public interface bookadmin_service {

    //图书展示
    public  String  show_book(Model model, Integer pn);


    //删除



    //更改信息


    //    书籍详情
    public  String   booksname(String bookname);
}
