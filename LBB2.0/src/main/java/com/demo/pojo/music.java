package com.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("music")
public class music {
    private String idname;
}
