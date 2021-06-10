package com.demo.controller;

import com.demo.Service.dataService;
import com.demo.Service.wxSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/wx")
public class wxconller {
    @Autowired
    wxSerivce wxSerivce;
    @GetMapping("/jiaodan")
    @ResponseBody
    String jiaodan(@RequestParam(value = "wxid") String wxid,
                   @RequestParam(value = "wxint") Integer wxint,
                   @RequestParam(value = "wxtext") String text, HttpServletRequest request) {
        wxSerivce.data(request,wxid,wxint,text);
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
        return  "";
  }

}
