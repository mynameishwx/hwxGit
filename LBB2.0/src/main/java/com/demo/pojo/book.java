package com.demo.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("book")
public class book {

     //书籍名称
    @TableId("bookName")
    private String bookName;

    public book() {
    }

    //书籍作者
    private String bookCreateName;

    //类别
    private  String  bookclass;

    //创建时间
    @TableField(value = "create_time" ,fill = FieldFill.INSERT)
    private Date  create_time;

    //修改时间
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date update_time;
}
