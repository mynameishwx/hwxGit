package com.demo.Service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.Service.bookService;
import com.demo.Service.bookadmin_service;
import com.demo.mapper.bookmapper;
import com.demo.pojo.Accoutuser;
import com.demo.pojo.book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import org.springframework.ui.Model;
@Service
public class bookadmin_impl implements bookadmin_service {


    @Autowired
    book book;

    @Autowired
    bookService bookService;
//    图书展示
    @Override
    public String show_book(Model model, Integer pn) {
        Page<book> bookPage=new Page<>(pn,5);
        Page page=bookService.page(bookPage,null);
        long current = page.getCurrent();  //当前页
        long pages = page.getPages();
        long total = page.getTotal();
        List<book> Records = page.getRecords();
        model.addAttribute("page",page);
        return "bookmadin";
    }

    @Override
    public String booksname(String bookname) {
        book.setBookname(bookname);
        List<book> books= bookService.getbook_hwx(book);
        Iterator<book> iterator=books.iterator();
        while (iterator.hasNext()){
            book=iterator.next();
            String text=book.getBooktext();
            return  text;
        }
        return "null";
    }
}

