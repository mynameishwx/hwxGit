package com.demo.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.pojo.Accoutuser;
import com.demo.pojo.bookmain;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface bookmainService extends IService<bookmain> {


      public  bookmain mybatis_getmajor(String id);

      public  Integer insert_major(bookmain bookmain);

     public  String  insert_hwx_book(MultipartFile file,String s,String s1);

      public List<bookmain> getbysessions(String session);



}
