package com.demo.Service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.Service.salaryService;

import com.demo.mapper.salarymapper;
import com.demo.pojo.Accoutuser;
import com.demo.pojo.music;
import com.demo.pojo.salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class salarySerivceimpl extends ServiceImpl<salarymapper,salary> implements salaryService {
    @Autowired
    salarymapper salarymapper;


    //依赖循环  自己导入自己
//    @Autowired
//    salaryService salaryService;


    salary salarypojo;
    Accoutuser accoutuser;

    @Override
    public String getsalary(Model math, Integer integer) {
//        Page<salary> page1= new Page<>(integer,5);
//        Page  page= salaryService.page(page1,null);
//        long current = page.getCurrent();  //当前页
//        long pages = page.getPages();
//        long total = page.getTotal();
//        List<music> Records = page.getRecords();
//        math.addAttribute("page",page);
        return "salary";
    }

    @Override
    public String salary(String work) {
        float time = 0;
        int salary;
        Map<Integer, String> weekMap = new HashMap<>(7);
        weekMap.put(1, "星期日");
        weekMap.put(2, "星期一");
        weekMap.put(3, "星期二");
        weekMap.put(4, "星期三");
        weekMap.put(5, "星期四");
        weekMap.put(6, "星期五");
        weekMap.put(7, "星期六");
        Calendar calendar = Calendar.getInstance();
        int xt=calendar.get(Calendar.DAY_OF_WEEK);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String  zctime=formatter.format(date);
        if(work=="zero_zero"){
            salary=0;
        }else {
            if (xt == 7 || xt == 1) {
                switch (work){
                    case "zero_three": time= 0.5F; break;
                    case "one": time=1;break;
                    case "one_three": time=1.5f;break;
                    case "two": time=2;break;
                    case "two_three":time=2.5f;break;
                    case "three": time=3;break;
                    default:time=0;break;
                }
                salary=8*23+(int)(time*23);
            } else {
                switch (work){
                    case "zero_three": time= 0.5F; break;
                    case "one": time=1;break;
                    case "one_three": time=1.5f;break;
                    case "two": time=2;break;
                    case "two_three":time=2.5f;break;
                    case "three": time=3;break;
                    default:time=0;break;
                }
                salary=(int)(time*17);
            }
        }
        String timeplus=time+"";
        salarymapper.setsalary("hwxadmin",zctime,timeplus,salary);
        return "成功";
    }

    @Override
    public Integer getSsum() {
        //返回查询的加班工资
        List<salary> salaryls=new ArrayList<>();

//        for(int x=0;x<salaryls.size();x++){
//            max=max+Integer.parseInt(salaryls.get(x).getPlus());
//            System.out.println(max);
//        }
        return salarymapper.getsalarysum();
    }
}
