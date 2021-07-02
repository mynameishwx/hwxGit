package com.demo.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.pojo.Accoutuser;
import com.demo.pojo.music;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface dataService extends IService<music> {
    public String user_id(String id,Model model);
    /*
       搜索展示
     */
     String  showService(Accoutuser accoutuser, Model model);
     /*
          删除
      */
    String  deleteSrvice(String delete, Integer pn, RedirectAttributes attributes);
    /*
         后台数据展示
     */
    String dataService(Model model,HttpServletRequest request,Integer integer);
//            个人简介
    String userService(HttpServletRequest request, Model model);


//           修改个人简介
    String useroneService(MultipartFile file, String s, Model model,String id);

//    下载音乐
    String musicService(HttpServletRequest request, HttpServletResponse httpServletResponse);

//    上传音乐
    String musicuploadService(MultipartFile file);

//    展示音乐
//    String showmusicService(Integer integer,Model model);

    public  Integer updata_acc_role(String acc_name,Integer rolename);
}
