package com.demo.controller;

import com.demo.Service.dataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;

@Controller
public class userController {

    @Autowired
    dataService dataService;

    @RequiresPermissions("admin:*:*")
   @GetMapping("/user")
    public  String  userone(HttpServletRequest request, Model model){
        return  dataService.userService(request,model);
    }

    @PostMapping("/userone")
    public  String  userOne(@RequestParam(value = "img",defaultValue = "") MultipartFile file,
                            @RequestParam(value = "name") String name,
                            Model model){
        dataService.useroneService(file,name,model);
        return "main";

    }

}
