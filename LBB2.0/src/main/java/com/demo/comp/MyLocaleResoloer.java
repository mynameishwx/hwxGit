package com.demo.comp;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
/*
*  split(“_”)   分割一个字符串 以split中的字符分割  如  ch_CN  则被分割为ch 和CN 两个字符串
*   这个是我们自己的区域信息解析器 实现 LocaleResolver 接口
*/
public class MyLocaleResoloer  implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
       String l= httpServletRequest.getParameter("l");   //获取参数的值
       Locale   locale=Locale.getDefault();
        if(!StringUtils.isEmpty(l)) {  //判断 l 中有没有带参数信息

         String[] spli=l.split("_");      //分割语言代码和国家代码
         locale= new Locale(spli[0],spli[1]);
       }
       return  locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
