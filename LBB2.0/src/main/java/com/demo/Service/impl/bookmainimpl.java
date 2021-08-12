package com.demo.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.Service.bookService;
import com.demo.Service.bookmainService;
import com.demo.mapper.bookmainmapper;
import com.demo.pojo.book;
import com.demo.pojo.bookmain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.List;

@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
public class bookmainimpl extends ServiceImpl<bookmainmapper, bookmain> implements bookmainService {
      @Autowired
     bookmainmapper bookmainmapper;

      @Autowired
      book book;

      @Autowired
    bookService bookService;


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

    @Override
    public String insert_hwx_book(MultipartFile file, String bookname, String bookcreate) {

        byte[] bytes=new byte[(int) file.getSize()];
        ByteBuffer byteBuffer=ByteBuffer.allocate((int) file.getSize());
        try {
            byteBuffer.put(file.getBytes());
            byteBuffer.flip();
            String s=new String(file.getBytes(),0,byteBuffer.limit());
            System.out.println(s);
            book.setBookCreate(bookcreate);
            book.setBookclass("测试");
            book.setBookName(bookname);
            bookService.insert_hwx_s(book);
        } catch (IOException e) {
            System.out.println("插入失败_来自_bookserviceimpl");
            e.printStackTrace();
        }


        return  "index";
    }
}
