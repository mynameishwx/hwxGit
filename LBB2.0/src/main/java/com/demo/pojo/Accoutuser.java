package com.demo.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;
import java.time.LocalDateTime;
import java.util.Date;

@Data  //隐藏了 set，get方法
//@Repository
@Validated
@TableName("domain")
public class Accoutuser {
    @NonNull
    private  String  id;  //用户名
    private  String  pass;  //密码
    private  String idtime;  //登录时间
    private  String idname;  //昵称

    @TableField(value = "zctime",fill = FieldFill.INSERT)
    private  Date zctime;  //注册时间

    private  String sex;   // 性别
    private  String ratio;   // 随机盐

    @TableLogic
    @TableField(value = "deleted",fill = FieldFill.INSERT)
    private  Integer  deleted;  // 逻辑删除

    private  byte[]  img;    //头像id


    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date create_time; //字段创建时间

//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private  Date  update_time;  //字段修改时间

    @Version
    @TableField(value = "version" ,fill = FieldFill.INSERT)
    private  Integer version;   //版本控制

    public Accoutuser(){

    }
}
