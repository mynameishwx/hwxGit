package com.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

@Data
@TableName("power")
public class power {
    @TableId
    @Column("powerId")
    private  Integer  powerId;  //权限ID


    @Column("powerName")
    private  String  powerName;  //权限

    @Column("powerUrl")
    private  String  powerUrl;  //权限url


}
