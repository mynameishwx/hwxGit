package com.demo.comp;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class mymybatis_plus implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
//        this.fillStrategy(metaObject,)
//        this.strictInsertFill(metaObject,"create_time",LocalDateTime.class,LocalDateTime.now());
//        this.strictInsertFill(metaObject,"update_time", LocalDateTime.class, LocalDateTime.now());
        this.setFieldValByName("version",1,metaObject);  //乐观锁
        this.setFieldValByName("deleted",0,metaObject);   //逻辑删除
        this.setFieldValByName("zctime",new Date(),metaObject);   //user注册时间
        this.setFieldValByName("create_time", new Date(),metaObject); //user创建时间


        this.setFieldValByName("majorCreateTime",new Date(),metaObject); //专业表创建时间
        this.setFieldValByName("majorUpdateTime",new Date(),metaObject); //专业表修改时间
        this.setFieldValByName("showTime",5000,metaObject);  //剩余阅读时间 (分钟)


        this.setFieldValByName("update_time",new Date(),metaObject);  //更新时间
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        this.strictUpdateFill(metaObject,"update_time",LocalDateTime.class,LocalDateTime.now());
         this.setFieldValByName("update_time",new Date(),metaObject);  //更新时间
    }
}
