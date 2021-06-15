package com.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

@Data
@TableName("acc_role")
public class acc_role {
    @TableId
    @Column("acc_roleId")
    private  Integer acc_roleId;  //用户角色ID

    @Column("accId")
    private  String  accId;  //用户ID

    @Column("roleId")
    private  Integer roleId; //角色ID
}
