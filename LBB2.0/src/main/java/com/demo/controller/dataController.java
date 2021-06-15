package com.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.Service.AccoutService;
import com.demo.Service.dataService;
import com.demo.pojo.Accoutuser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@Controller
@RequiresPermissions("admin:*:*")
@RequestMapping(value = "/data",method = RequestMethod.GET)
public class dataController {
   @Autowired
    dataService dataService;



   @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
   public  String  user_id(@PathVariable(value = "id") String id,Model model){
       return dataService.user_id(id,model);
   }

//    查询
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public  String show(@RequestParam(value = "id",defaultValue = "") String id,
                        @RequestParam(value = "sex",defaultValue = "")String sex,
            @RequestParam(value = "nic",defaultValue = "")String nic
            ,Model model){
        Accoutuser accoutuser=new Accoutuser();
        if(!id.equals("")){
            accoutuser.setId(id);
        }
        if(!sex.equals("")){

            accoutuser.setSex(sex);
        }
        if(!nic.equals("")){

            accoutuser.setIdname(nic);
        }
        String url=dataService.showService(accoutuser,model);

        return  url;
    }

    /*
       取 /delete/{deleteOne} 这个中参数 deleteOne 的值用  @PathVariable
       RedirectAttributes 这个用于重定向中加参数
     */
//删除操作
    @RequestMapping(value = "/delete/{deleteOne}",method = RequestMethod.GET)
    public  String  delete(@PathVariable(value = "deleteOne") String deleteOne
            , @RequestParam(value = "pn",defaultValue = "1") Integer pn
            , RedirectAttributes attributes){
       return  dataService.deleteSrvice(deleteOne,pn,attributes);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public  String data(Model model, HttpServletRequest request
           , @RequestParam(value = "pn",defaultValue = "1") Integer pn){
      return  dataService.dataService(model,request,pn);
    }


}
