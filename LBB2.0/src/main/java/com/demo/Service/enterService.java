package com.demo.Service;

import com.demo.pojo.Accoutuser;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public interface enterService {

//       登录
   public String dl_index(String id, String pass, HttpSession session, HttpServletRequest request,Map<String,Object> mapone);


//      注册
    String  zc_index(Accoutuser accoutuser,Map<String, Object> map);


//       ajax判断用户名是否为空
    public String ajaxenterService(HttpServletRequest request, HttpServletResponse httpServletResponse);


//        ajax测试(bmi)
    String ajaxService(HttpServletRequest request, HttpServletResponse httpServletResponse);


//        ajaxbmi计算
    String  ajaxoneService(HttpServletRequest request, HttpServletResponse httpServletResponse);



}
