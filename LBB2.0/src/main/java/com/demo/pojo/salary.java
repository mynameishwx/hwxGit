package com.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("salary")
public class salary {
    @TableId(type = IdType.AUTO)
    /*
      NONE :未设置主键
      AUTO :主键自增
      INPUT :手动输入
      ASSIGN_UUID :uuid
     */
    private  Integer  id;
    private  String  name;
    private  String  time;
    private  String  plus;
    private  Integer salary;
}
