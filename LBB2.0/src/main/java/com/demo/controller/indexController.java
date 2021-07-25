package com.demo.controller;

import com.demo.Service.bookmainService;
import com.demo.Service.enterService;
import com.demo.pojo.Accoutuser;
import com.demo.pojo.bookmain;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller

public class indexController {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    enterService  enterService;

    @Autowired
    bookmainService bookmainService;


   @PostMapping("/dlindex")

   public String index_three(@RequestParam(value = "name",defaultValue = "") String id,
                             @RequestParam("password") String password,
                             Map<String,Object> mapone, HttpSession session, HttpServletRequest request){
                        Subject subject= SecurityUtils.getSubject();
                        try {
                            if(id!="" && password!=""){
                                String s= (String) subject.getPrincipal();
                               if(s==null){
                                   UsernamePasswordToken token=new UsernamePasswordToken(id,password);
                                   subject.login(token);
                                   boolean permitted = subject.isPermitted("admin:*:*");
                                   System.out.println(permitted);
                                   String tishi=enterService.dl_index(id,password,session,request,mapone);
                                   String[]  ok=tishi.split("&");
                                   mapone.put("return_dl",ok[0]);
                                  if(ok.length>=2){
                                      mapone.put("return_admin",ok[1]);
                                  }
                                   if(redisTemplate.opsForValue().get(id)!=null)
                                       redisTemplate.delete(id);
                                   if(redisTemplate.opsForValue().get(id+"_time")!=null){
                                       mapone.put("id","已锁定，请2分钟后再登录！");
                                       return  "enter";
                                   }
                                   return "index";
                               }else {
                                   mapone.put("id","已登录，请退出后再登录！");
                                   return "enter";
                               }
                            }else {
                                mapone.put("id","密码或账号错误");
                                return "enter";
                            }
                         }catch (UnknownAccountException e){
                             mapone.put("id","用户不存在");
                             return "enter";
                         }catch (IncorrectCredentialsException e){
                            int zhi;
                            if(redisTemplate.opsForValue().get(id)==null)
                                 redisTemplate.opsForValue().set(id,1);
                             else
                             {
                                zhi= (int) redisTemplate.opsForValue().get(id);
                                 redisTemplate.opsForValue().set(id,zhi+1);
                             }
                            zhi= (int) redisTemplate.opsForValue().get(id);
                             if(zhi==3){
                                 redisTemplate.opsForValue().set(id+"_time",0,2, TimeUnit.MINUTES);
                             }
                             mapone.put("password","密码错误");
                             return "enter";
                         }
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
          if(!mainname.equals("") && !secondaryname.equals("")){
              if(!mainname.equals(secondaryname)){
                  accoutuser.setIdname(idname);
                  accoutuser.setPass(password);
                  bookmain.setId(idname);
                  String major=mainname+","+secondaryname;
                  bookmain.setMajor(major);
//                  bookmainService.insert_major(bookmain);
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


