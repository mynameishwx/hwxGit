package com.demo.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.pojo.salary;
import org.springframework.ui.Model;

public interface salaryService extends IService<salary> {
//    工资计算
    String salary(String work);
//    工资查询
    String getsalary(Model math, Integer integer);
//    总加班工资查询
    Integer getSsum();
}
