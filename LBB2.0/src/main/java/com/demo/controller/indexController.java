package com.demo.controller;

import com.demo.Service.bookmainService;
import com.demo.Service.enterService;
import com.demo.pojo.Accoutuser;
import com.demo.pojo.bookmain;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller

public class indexController {
    @Autowired
    enterService  enterService;

    @Autowired
    bookmainService bookmainService;

   @PostMapping("/dlindex")
   public String index_three(@RequestParam(value = "name",defaultValue = "") String id,
                             @RequestParam("password") String password,
                             Map<String,Object> mapone, HttpSession session, HttpServletRequest request){

           return   enterService.dl_index(id,password,session,request,mapone);
   }

   @PostMapping("/zcindex")
    public String Indextwo(
      @RequestParam("nic") String idname,@RequestParam("name") String id,
      @RequestParam("password") String password,
      @RequestParam("rePassword") String rePassword,
      @RequestParam(value = "sex",defaultValue = "") String sex,
      @RequestParam(value = "main",defaultValue = "") String mainname,
      @RequestParam(value = "secondary",defaultValue = "") String secondaryname,
      Map<String, Object> map){
       Accoutuser accoutuser=new Accoutuser();
       bookmain  bookmain=new bookmain();
      if(rePassword.equals(password)){
          accoutuser.setId(id);
          accoutuser.setSex(sex);
          if(idname==null || idname.equals("")){
              idname=id;
          }
          Subject subject= SecurityUtils.getSubject();
          subject.login(new UsernamePasswordToken(id,password));
          if(!mainname.equals("") && !secondaryname.equals("")){
              if(!mainname.equals(secondaryname)){
                  accoutuser.setIdname(idname);
                  accoutuser.setPass(password);

                  bookmain.setId(idname);
                  String major=mainname+","+secondaryname;
                  bookmain.setMajor(major);
                  bookmainService.insert_major(bookmain);
              }else {
                  map.put("rewasswordWarn","不能选择两个相同的专业");
                  return   "index_two";
              }
          }else {
              map.put("rewasswordWarn","专业不能为空");
              return   "index_two";
          }

      }else
      {
          map.put("rewasswordWarn","密码不一致");
                    return   "index_two";
      }
     return   enterService.zc_index(accoutuser,map);

    }
}


