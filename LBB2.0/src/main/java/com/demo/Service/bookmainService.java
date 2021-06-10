package com.demo.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.pojo.Accoutuser;
import com.demo.pojo.bookmain;

import java.util.List;

public interface bookmainService extends IService<bookmain> {


      public  bookmain mybatis_getmajor(String id);

      public  Integer insert_major(bookmain bookmain);


      public List<bookmain> getbysessions(String session);



}
