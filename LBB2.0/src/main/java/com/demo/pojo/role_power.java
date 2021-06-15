package com.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

@Data
@TableName("role_power")
public class role_power {
    @TableId
    @Column("role_powerId")
    private  Integer role_powerId;  //角色权限Id


    @Column("roleId")
    private  Integer roleId;  //角色Id

    @Column("powerId")
    private  Integer powerId;  //权限ID
}
