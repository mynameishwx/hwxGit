package com.demo.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

import java.util.Date;

@Data
@TableName("book")
public class book {

    @TableId("bookid")
    private  Integer  bookid;

     //书籍名称
    @Column("bookname")
    private String bookname;

    public book() {
    }

    //书籍作者
    @Column("bookcreate")
    private String bookcreate;

    //类别
    @Column("bookclass")
    private  String  bookclass;

//    内容
    @Column("booktext")
    private  String  booktext;

    //创建时间
    @TableField(value = "create_time" ,fill = FieldFill.INSERT)
    private Date  create_time;

    //修改时间
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date update_time;
}
