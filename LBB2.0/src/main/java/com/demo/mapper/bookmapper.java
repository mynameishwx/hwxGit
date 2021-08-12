package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.pojo.book;
import org.apache.ibatis.annotations.Mapper;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@Mapper
public interface bookmapper extends BaseMapper<book> {
      public List<book>  getbookclass(String bookclass);

      public Integer insert_hwx(book book);  //插入

      
}
