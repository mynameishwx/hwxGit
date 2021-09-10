package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.pojo.acc_book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface acc_bookMapper extends BaseMapper<acc_book> {

//      插入(hwx)
      public  Integer insert_hwx(acc_book acc_book);

//      更改
      public  Integer  alter_hwx(acc_book acc_book);
}
