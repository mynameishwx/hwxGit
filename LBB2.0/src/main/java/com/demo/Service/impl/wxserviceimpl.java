package com.demo.Service.impl;

import com.demo.Service.AccoutService;
import com.demo.Service.wxSerivce;
import com.demo.mapper.wxmapper;
import com.demo.pojo.Accoutuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class wxserviceimpl implements wxSerivce {
    @Autowired
    wxmapper wxmapper;
    @Autowired
    AccoutService accoutService;
    Accoutuser accoutuser;
    public String data(HttpServletRequest request, String wxid, Integer wxint, String text) {
        Object yu=request.getSession().getAttribute("yanzhen");
        //查询登录用户的用户名
        accoutuser= accoutService.getById(yu.toString());
        int x=0;
        int i=0;
        String  ceshi1="";
        String[] ceshi2=new String[100];
        String[] tt=text.split("");
        String[] ok=new String[tt.length/11];
        for(int o=0;o<tt.length;o++){
            switch (tt[o]) {
                case "1":
                    x = x + 1;
                    break;
                case "2":
                    x = x + 1;
                    break;
                case "3":
                    x = x + 1;
                    break;
                case "4":
                    x = x + 1;
                    break;
                case "5":
                    x = x + 1;
                    break;
                case "6":
                    x = x + 1;
                    break;
                case "7":
                    x = x + 1;
                    break;
                case "8":
                    x = x + 1;
                    break;
                case "9":
                    x = x + 1;
                    break;
                case "0":
                    x = x + 1;
                    break;
                default:x=0;
            }
            if(x<=11){
                ceshi1=ceshi1+tt[o];
            }
            if(x==11){
                ok[i]=ceshi1;
                ceshi1="";
                i++;
            }
        }
        for(int rt=0;rt<ok.length;rt++){
           if(ok[rt]!=null){
               System.out.println(rt+1+"->"+ok[rt]);
           }
        }
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String  zctime=formatter.format(date);
        for(int wx=0;wx<ok.length;wx++){
            if(ok[wx]!=null){
                wxmapper.setdata(accoutuser.getId(),ok[wx],zctime,wxid);
            }else break;
        }
        return  "";
    }
}
