package com.demo.controller;

import com.demo.Service.salaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/salary")
public class salarycontroller {
   @GetMapping("")
    public  String salary(Model model, @RequestParam(value = "pn",defaultValue = "1") Integer pn){

        return salaryService.getsalary(model,pn);
    }


    @Autowired
    salaryService  salaryService;
//   工资计算
    @GetMapping("/data")
    @ResponseBody
    public  String salarydata(@RequestParam(value = "work") String work){
        return salaryService.salary(work);
    }

//    总加班工资计算
    @GetMapping("/sum")
    @ResponseBody
    public  Integer getsum(){
        return  salaryService.getSsum();
    }
}


