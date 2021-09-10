package com.demo.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.Service.acc_bookService;
import com.demo.mapper.acc_bookMapper;
import com.demo.pojo.acc_book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class acc_bookimpl extends ServiceImpl<acc_bookMapper, acc_book> implements acc_bookService {

    @Autowired
    acc_bookMapper acc_bookMapper;

    @Autowired
    acc_book acc_book;

    @Override
    public acc_book getacc_book_mybatis(String acc_book_id) {
        acc_book=acc_bookMapper.selectById(acc_book_id);
        return acc_book;
    }

    @Override
    public Integer insert_mybatis(acc_book book) {
        return acc_bookMapper.insert(book);
    }

    @Override
    public Integer delete_mybatis(acc_book book) {
        Integer  id=book.getId();
        return acc_bookMapper.deleteById(id);
    }

    @Override
    public Integer alter_hwx(acc_book acc_book) {
        return acc_bookMapper.alter_hwx(acc_book);
    }

    @Override
    public Integer insert_hwx(com.demo.pojo.acc_book acc_book) {
//        return  acc_bookMapper.insert(acc_book);
        return acc_bookMapper.insert_hwx(acc_book);
    }
}
