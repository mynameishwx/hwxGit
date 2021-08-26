package com.demo.Service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.Service.bookService;
import com.demo.mapper.bookmapper;
import com.demo.pojo.book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
public class bookimpl extends ServiceImpl<bookmapper, book> implements bookService {

    @Autowired
    book book;
       @Autowired
       bookmapper bookmapper;
       //查询
       @Override
       public  book  getbyid_mybatis(String name){
           return  bookmapper.selectById(name);
       }



    //       插入
    @Override
    public String insert_hwx_book(MultipartFile file, book book) {
        ByteBuffer byteBuffer=ByteBuffer.allocate((int) file.getSize());
        try {
            InputStream inputStream=file.getInputStream();
            byte[] bytes=new byte[(int) file.getSize()];
            inputStream.read(bytes);
            String s=new String(bytes,0,byteBuffer.limit());
              book.setBooktext(s);
            bookmapper.insert_hwx(book);
        } catch (IOException e) {
            List<book> books=bookmapper.getbook_hwx(book);
            Iterator<book> integer=books.iterator();
            while (integer.hasNext()){
                book=integer.next();
                bookmapper.deleteById(book.getBookid());
            }
            e.printStackTrace();
            System.out.println("bookimpl");
        }
        return null;
    }

    @Override
    public List<com.demo.pojo.book> getbook_hwx(com.demo.pojo.book book) {
           if(book!=null){
               List<book> books=bookmapper.getbook_hwx(book);
               return books;
           }else {
               System.out.println("bookimpl的getbook_hwx参数book为空");
               return null;
           }
    }

//    mybatis 的插入
    @Override
    public Integer insert_mybatis_s(book book) {

        return bookmapper.insert(book);

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
    public  Integer alter_bookuser(book book){
           return  bookmapper.updateById(book);
    }
}
