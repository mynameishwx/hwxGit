package com.demo.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

import java.util.Date;

@Data
@TableName("major")
public class bookmain {
    @TableId(value = "id")
    private  String  id;

    @Column(value = "major")
    private  String  major;

    @TableField(value = "showtime",fill = FieldFill.INSERT)
    private  Integer showTime;

    @TableField(value = "majorCreateTime",fill = FieldFill.INSERT)
    @Column(value = "majorCreateTime")
    private  Date  majorCreateTime;

    @TableField(value = "majorUpdateTime",fill = FieldFill.INSERT_UPDATE)
    @Column(value = "majorUpdateTime")
    private  Date  majorUpdateTime;


    @Column(value = "bookclass")
    private  String  bookclass;

    @Column(value = "loin")
    private  Long  loin;


    @Column(value = "session")
    private  String session;
    public bookmain() {
    }
}
