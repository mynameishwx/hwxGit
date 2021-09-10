package com.demo.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import java.util.Date;

@Data
@TableName("acc_book")
public class acc_book {

    @TableId
    private  Integer  id;     //id

    @Column("bookid")
    private  Integer  bookid;   //书籍id


    @Column("accid")
    private  String  accid;    //用户id


    @TableField(value = "create_time" ,fill = FieldFill.INSERT)
    @Column("createtime")
    private  Date  createtime;  //借阅开始时间


    @Column("time")
    private  Integer  time; //借阅时间

    @Column("state")
    private  String state;  //状态

}
