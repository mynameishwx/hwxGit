package com.demo.Service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.Service.AccoutService;
import com.demo.Service.acc_roleService;
import com.demo.Service.dataService;
import com.demo.Service.roleService;
import com.demo.mapper.*;
import com.demo.pojo.Accoutuser;
import com.demo.pojo.acc_role;
import com.demo.pojo.music;
import com.demo.pojo.role;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class dataServiceImpl extends ServiceImpl<Musicmapper,music> implements dataService {

    @Autowired
    AccoutService accoutService;

//    @Autowired
//    dataService dataService;
    @Autowired
    Musicmapper musicmapper;

    @Autowired
    Accoutmapper accoutmapper;

    @Autowired
    bookmainmapper bookmainmapper;

    @Autowired
    Accoutuser accoutuser;

    @Autowired
    role role;

    @Autowired
    acc_roleService acc_role;

    @Autowired
    roleService roleService;

    @Autowired
    acc_role acc_roletwo;
    @Override
    public String user_id(String id,Model model){
        accoutuser=accoutmapper.selectById(id);
        acc_roletwo.setAccId(id);
        Iterator<com.demo.pojo.acc_role> iterator=acc_role.getbyname_hwx(acc_roletwo).iterator();
       while (iterator.hasNext()){
           acc_roletwo=iterator.next();
        role=roleService.getById(acc_roletwo.getRoleId());
           model.addAttribute("role_h",role.getRoleName());
       }
        model.addAttribute("acc",accoutuser);
        return "user_id";
    }

    //查询
    @Override
    public String showService(Accoutuser accoutuser, Model model) {
        Page<Accoutuser> page1= new Page<>();
        Page  page= accoutService.page(page1,null);
        long current = page.getCurrent();  //当前页
        long pages = page.getPages();
        long total = page.getTotal();
        List<Accoutuser> Records = page.getRecords();
        model.addAttribute("page",page);
//        if(accoutuser.getId()!=null && !accoutuser.getId().equals("")){
//            String idp="%"+accoutuser.getId()+"%";
//           accoutuser.setId(idp);
//        }

        List<Accoutuser> serviceid= accoutService.getServiceIdone(accoutuser);


        model.addAttribute("serviceid",serviceid);
        model.addAttribute("data_yanztwo","true");
        model.addAttribute("data_yanzone","false");
        return  "data";
    }

    //删除
    @Override
    public String deleteSrvice(String delete, Integer pn, RedirectAttributes attributes) {
//        //进行逻辑删除
//        accoutmapper.deleteById(delete);

//        实际删除（数据库删除）
       if(!delete.equals("hwxadmin"))
       {
           Accoutuser getid = accoutmapper.getid(delete);
           String img = getid.getImg();
           if(!img.equals("hwx")){
               String url="D:/LBB2.0/Data/img/userimg/"+img+".jpg";
               File file=new File(url);
               if(file.exists()){
                   file.delete();
               }
           }
           accoutService.removeById(delete);

           accoutuser =new Accoutuser();
           accoutuser.setId(delete);
           accoutService.mydeletebyid(accoutuser);  //实际删除
           bookmainmapper.deleteById(delete); //删除专业表中的数据
       }
        attributes.addAttribute(pn);  //重定向中加参数
        return "redirect:/data";
    }

    //展示
    @Override
    @RequiresPermissions("admin:query:*")
    public String dataService(Model model, HttpServletRequest request, Integer integer) {
        Object one=request.getSession().getAttribute("yanzhen");
            Page<Accoutuser> page1= new Page<>(integer,5);
            Page  page= accoutService.page(page1,null);
            long current = page.getCurrent();  //当前页
            long pages = page.getPages();
            long total = page.getTotal();
            List<Accoutuser> Records = page.getRecords();
            model.addAttribute("page",page);
            model.addAttribute("data_yanzone","true");
            model.addAttribute("data_yanztwo","false");
            return "data";
    }

    @Override
    public String userService(HttpServletRequest request, Model model) {
        Object o=request.getSession().getAttribute("yanzhen");
        //查询登录用户的用户名
        accoutuser= accoutService.getById(o.toString());
            String  ceshi="/userimg/"+accoutuser.getImg()+".jpg";
            model.addAttribute("img",ceshi);
//       昵称
        model.addAttribute("idname",accoutuser.getIdname());
//       账号
        model.addAttribute("id",accoutuser.getId());

        return "user";
    }

    @Override
    public String useroneService(MultipartFile file, String name, Model model,String id) {

        String  tihuan=accoutuser.getImg();
        Accoutuser accoutusert=new Accoutuser();
        accoutusert.setId(id);
        if(file.getSize()!=0){
            String url="D:/hwxGit/LBB2.0/Data/img/userimg/";
            String imgname=file.getOriginalFilename(); //获取图片名称
            //用时间来编号用户头像
            SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date(System.currentTimeMillis());
            String  zctime=formatter.format(date);
            tihuan=zctime;
            try {
                FileOutputStream piOutputStream=new FileOutputStream(url+zctime+".jpg");
                piOutputStream.write(file.getBytes());//获取字节流直接写入到磁盘内
                piOutputStream.close();//关闭字节流

                //自己的修改函数
//                accoutmapper.alteruser(accoutusert);

                //乐观锁的先查询
                accoutusert=accoutmapper.selectById(accoutusert.getId());

                accoutusert.setImg(zctime);

                //mybatisplus的修改函数
                accoutmapper.updateById(accoutusert);
            }  catch (IOException e){
                System.out.println("userController的IO报错");
                e.printStackTrace();
            }
        }
        //等于空就不修改
        if(accoutuser!=null){
            if (name!=null && !name.equals(""))
            {
                //自己的修改函数
//            accoutmapper.alteruser(accoutusert);

                //乐观锁的先查询
                accoutusert=accoutmapper.selectById(accoutusert.getId());

                accoutusert.setIdname(name);
                //mybatisPlus 的修改函数
                accoutmapper.updateById(accoutusert);

                model.addAttribute("id",name);
            }
        }
        return "user";
    }
//下载music
    @Override
    public String musicService(HttpServletRequest request, HttpServletResponse httpServletResponse) {

        return null;
    }
//    上传music
    @Override
    public String musicuploadService(MultipartFile file) {
        if(file!=null && !file.equals(""))
        {
            String musicname=file.getOriginalFilename();
            String[] tt=musicname.split(".");
            String url="D:/LBB2.0/Data/music/";
            System.out.println(musicname);
            try {
                FileOutputStream piOutputStream=new FileOutputStream(url+musicname);
                piOutputStream.write(file.getBytes());//获取字节流直接写入到磁盘内
                piOutputStream.close();//关闭字节流
                musicmapper.setname(tt[0]);
            }catch (IOException e){
                System.out.println("上传文件出现io问题");
                e.printStackTrace();
                return  "查询io错误上传失败";
            }
        }
        return  "上传成功";
    }

//    @Override
//    public String showmusicService(Integer integer,Model model) {
//
//        /*
//          page后面两个参数
//
//          第一个参数：代表页数 ，这里将前端请求的页数传到这
//
//          第二个参数：代表这页显示的条数
//         */
////        Page<music> page1= new Page<>(integer,5);
////        Page  page= dataService.page(page1,null);
////        long current = page.getCurrent();  //当前页
////        long pages = page.getPages();
////        long total = page.getTotal();
////
////        //把他添加到list中，然后通过model发送到前端
////        List<music> Records = page.getRecords();
////        model.addAttribute("page",page);
//////        model.addAttribute("url","/play/"+);
//////       List<music>  music=musicmapper.getname();
//////       model.addAttribute("music",music);
////        return "music";
////    }
}