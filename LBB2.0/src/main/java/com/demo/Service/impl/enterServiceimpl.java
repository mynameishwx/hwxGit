package com.demo.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.Service.AccoutService;
import com.demo.Service.enterService;
import com.demo.comp.myhand;
import com.demo.mapper.Accoutmapper;
import com.demo.pojo.Accoutuser;
import com.demo.pojo.bookmain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class enterServiceimpl extends ServiceImpl<Accoutmapper,Accoutuser> implements enterService {

    @Autowired
    AccoutService accoutService;

   @Autowired
    Accoutuser accoutuser;

    @Autowired
    bookmainimpl bookmainimpl;
     @Override
    public String dl_index(String id, String password, HttpSession session,HttpServletRequest request, Map<String,Object> mapone) {
        if((accoutuser=accoutService.getServiceid(id))!=null){
                mapone.put("idname", accoutuser.getIdname());
                mapone.put("id", accoutuser.getId());
                mapone.put("time",accoutuser.getIdtime());
//                登录时间
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(System.currentTimeMillis());
                String  idtime=formatter.format(date);

                accoutService.setServiceIdtime(idtime,id);  //写入登录时间
                session.setAttribute("yanzhen",id);  //登录拦截器
                if(id.equals("hwxadmin"))
                {
                    mapone.put("admin","后台管理");
                }
                bookmain bookmain=new bookmain();
                bookmain=bookmainimpl.mybatis_getmajor(id);
                if(bookmain!=null){
                    if(bookmain.getShowTime()<=0){
                        mapone.put("password","你已被强制下线!");
                        return "enter";
                    }
                }else {

                }
                return "main";
        }
        else {
            mapone.put("id","该账号未注册  去注册");
            return "enter";
        }
    }

    @Override
    public String zc_index(Accoutuser accoutusertwo,Map<String,Object> map) {
        if (!accoutusertwo.getId().equals("")) {
            if(accoutusertwo.getPass()!=""){
//                if(rePassword.equals(password)){
                    if(!accoutusertwo.getSex().equals("")){
//                           注册时间
                        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(System.currentTimeMillis());
                        String  zctime=formatter.format(date);
                        if((accoutuser=accoutService.getById(accoutusertwo.getId()))!=null)
                        {
                            map.put("tishi","该账号已注册，请登录!");
                            return  "enter";
                        }
                        else
                        {
                            map.put("zccg","注册成功，请登录!");
                           if (accoutService.setbyuser(accoutusertwo)!=0)
                               return  "enter";
                           else return "5xx";
                        }
                    }
                    else {
                        map.put("sex","选择性别");
                        return   "index_two";
                    }
//                }
//                else {
//                    map.put("id",id);
//                    map.put("rewasswordWarn","密码不一致");
//                    return   "index_two";
//                }
            }
            else {

                map.put("passwordWarn","请输入密码");
                return "index_two";
            }
        } else {
            map.put("idWarn", "请输入账号名");
            return "index_two";
        }
    }


    @Override
    public String ajaxenterService(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String name= request.getParameter("name");
        String ip=new myhand().getIpAddress(request);
        if(name!=null && null!=""){
            if((accoutService.getServiceid(name))==null){
//                return  "name为:"+name;
                return "此用户名未注册！";
            }
            else{
                return "";
            }
        }else {
            return  "用户名不能为空";
        }
    }

    @Override
    public String ajaxService(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        System.out.println("ajax");
        return  "ajax成功";
    }

    @Override
    public String ajaxoneService(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String  w=   request.getParameter("w");
        String h= request.getParameter("h");
        String name=request.getParameter("name");
        float bmi;
        String tt;
        try {
            float m=Float.valueOf(w);
            float y=Float.valueOf(h);
            bmi=m/(y*y);
            tt=",你的BMI值为:"+bmi+"";
        }
        catch (Exception e){
            tt=",你输入数字行不，别乱输入，宝贝";
            e.printStackTrace();
        }
        return  tt;
    }

}
