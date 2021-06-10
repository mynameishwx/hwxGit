package com.demo.controller;


import com.demo.Service.enterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/enter",method = RequestMethod.GET)
public class enterCont {
    @Autowired
    enterService enterService;
//     ajax判断用户名是否为空
    @RequestMapping(path = "/ajaxgetid",method = RequestMethod.POST)
    @ResponseBody
    public String ajaxenter(HttpServletRequest request, HttpServletResponse httpServletResponse){
       return  enterService.ajaxenterService(request,httpServletResponse);
    }

////    ajax测试(bmi)
//    @RequestMapping(value = "/myajax",method = RequestMethod.POST)
//    @ResponseBody
//    public  String ajax(HttpServletRequest request, HttpServletResponse httpServletResponse){
//        return  enterService.ajaxService(request,httpServletResponse);
//
//    }
//
////    ajaxbmi计算
//    @RequestMapping(value = "/myajaxone",method = RequestMethod.POST)
//    @ResponseBody
//    public String  ajaxone(HttpServletRequest request, HttpServletResponse httpServletResponse) {
//        return enterService.ajaxoneService(request, httpServletResponse);
//    }
}
