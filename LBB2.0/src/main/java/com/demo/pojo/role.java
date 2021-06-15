package com.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
@Data
@TableName("role")
public class role {

    @Column("roleId")
    @TableId
    private  Integer  roleId;  //角色ID

    @Column("roleName")
    private  String  roleName;  //角色
}
