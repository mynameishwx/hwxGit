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
import java.util.ArrayList;
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


    public Integer updata_acc_role(String acc_name,Integer rolename){
        if(acc_name!=null && acc_name!=""){
            acc_roletwo.setRoleId(null);
            acc_roletwo.setAccId(acc_name);
            List<acc_role> acc_roles=new ArrayList<>();
            acc_roles=acc_role.getbyname_hwx(acc_roletwo);
            Iterator<com.demo.pojo.acc_role> iterator=acc_roles.iterator();
            while (iterator.hasNext()){
                acc_roletwo=iterator.next();
                acc_roletwo.setRoleId(rolename);
                acc_roletwo.setAccId(acc_name);
                if(acc_roletwo.getAcc_roleId()!=null)
              return   acc_role.updata_hwx(acc_roletwo);
                else System.out.println(acc_roletwo.getAcc_roleId());
            }
         if(iterator.hasNext()==false)
         {
             acc_roletwo.setRoleId(rolename);
             acc_roletwo.setAccId(acc_name);
           int x=  acc_role.insert_hwx(acc_roletwo);
           return 3;
         }
        }
        return 0;
    }



    @Override
    public String user_id(String id,Model model){
        accoutuser=accoutmapper.selectById(id);
        acc_roletwo.setAccId(id);
//        List<acc_role> acc_roles=acc_role.getbyname_hwx()
        Iterator<com.demo.pojo.acc_role> iterator=acc_role.getbyname_hwx(acc_roletwo).iterator();
       while (iterator.hasNext()){
           acc_roletwo=iterator.next();
        role=roleService.getByid_mybatis(acc_roletwo.getRoleId());
           model.addAttribute("role_h",role.getRoleName());
       }
        model.addAttribute("id",id);
        model.addAttribute("acc",accoutuser);
        return "user_id";
    }

    //??????
    @Override
    public String showService(Accoutuser accoutuser, Model model) {
        Page<Accoutuser> page1= new Page<>();
        Page  page= accoutService.page(page1,null);
        long current = page.getCurrent();  //?????????
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

    //??????
    @Override
    public String deleteSrvice(String delete, Integer pn, RedirectAttributes attributes) {
//        //??????????????????
//        accoutmapper.deleteById(delete);

//        ?????????????????????????????????
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
           accoutService.mydeletebyid(accoutuser);  //????????????
           bookmainmapper.deleteById(delete); //???????????????????????????
       }
        attributes.addAttribute(pn);  //?????????????????????
        return "redirect:/data";
    }

    //??????
    @Override
    @RequiresPermissions("admin:query:*")
    public String dataService(Model model, HttpServletRequest request, Integer integer) {
        Object one=request.getSession().getAttribute("yanzhen");
            Page<Accoutuser> page1= new Page<>(integer,5);
            Page  page= accoutService.page(page1,null);
            long current = page.getCurrent();  //?????????
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
        //??????????????????????????????
        accoutuser= accoutService.getById(o.toString());
            String  ceshi="/userimg/"+accoutuser.getImg()+".jpg";
            model.addAttribute("img",ceshi);
//       ??????
        model.addAttribute("idname",accoutuser.getIdname());
//       ??????
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
            String imgname=file.getOriginalFilename(); //??????????????????
            //??????????????????????????????
            SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date(System.currentTimeMillis());
            String  zctime=formatter.format(date);
            tihuan=zctime;
            try {
                FileOutputStream piOutputStream=new FileOutputStream(url+zctime+".jpg");
                piOutputStream.write(file.getBytes());//???????????????????????????????????????
                piOutputStream.close();//???????????????

                //?????????????????????
//                accoutmapper.alteruser(accoutusert);

                //?????????????????????
                accoutusert=accoutmapper.selectById(accoutusert.getId());

                accoutusert.setImg(zctime);

                //mybatisplus???????????????
                accoutmapper.updateById(accoutusert);
            }  catch (IOException e){
                System.out.println("userController???IO??????");
                e.printStackTrace();
            }
        }
        //?????????????????????
        if(accoutuser!=null){
            if (name!=null && !name.equals(""))
            {
                //?????????????????????
//            accoutmapper.alteruser(accoutusert);

                //?????????????????????
                accoutusert=accoutmapper.selectById(accoutusert.getId());

                accoutusert.setIdname(name);
                //mybatisPlus ???????????????
                accoutmapper.updateById(accoutusert);

                model.addAttribute("id",name);
            }
        }
        return "user";
    }
//??????music
    @Override
    public String musicService(HttpServletRequest request, HttpServletResponse httpServletResponse) {

        return null;
    }
//    ??????music
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
                piOutputStream.write(file.getBytes());//???????????????????????????????????????
                piOutputStream.close();//???????????????
                musicmapper.setname(tt[0]);
            }catch (IOException e){
                System.out.println("??????????????????io??????");
                e.printStackTrace();
                return  "??????io??????????????????";
            }
        }
        return  "????????????";
    }

//    @Override
//    public String showmusicService(Integer integer,Model model) {
//
//        /*
//          page??????????????????
//
//          ?????????????????????????????? ??????????????????????????????????????????
//
//          ?????????????????????????????????????????????
//         */
////        Page<music> page1= new Page<>(integer,5);
////        Page  page= dataService.page(page1,null);
////        long current = page.getCurrent();  //?????????
////        long pages = page.getPages();
////        long total = page.getTotal();
////
////        //???????????????list??????????????????model???????????????
////        List<music> Records = page.getRecords();
////        model.addAttribute("page",page);
//////        model.addAttribute("url","/play/"+);
//////       List<music>  music=musicmapper.getname();
//////       model.addAttribute("music",music);
////        return "music";
////    }
}