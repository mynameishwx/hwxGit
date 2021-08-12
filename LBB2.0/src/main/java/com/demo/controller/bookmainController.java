package com.demo.controller;

import com.demo.Service.bookService;
import com.demo.Service.bookmainService;
import com.demo.Service.impl.bookimpl;
import com.demo.Service.impl.bookmainimpl;
import com.demo.comp.bookdate;
import com.demo.pojo.book;
import com.demo.pojo.bookmain;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bookmain")
public class bookmainController{
    @Autowired
    bookmainService bookmainService;

    @Autowired
    bookimpl bookimpl;

    @Autowired
    bookmainimpl bookmainimpl;

    bookmain bookmain;
    @GetMapping("")
    public  String  aotobookmain(HttpServletRequest request,Map map){
        Object id=request.getSession().getAttribute("yanzhen");
        bookmain=new bookmain();
        bookmain=bookmainService.mybatis_getmajor((String) id);
        if(bookmain!=null){
            if(bookmain.getMajor()!=null && bookmain.getMajor()!=""){
                String[]  maint=bookmain.getMajor().split(",");
                map.put("main",maint[0]);
                map.put("Secondary",maint[1]);
            }
        }
        return "bookmain";
    }

    @GetMapping("/books/{name}")
    public  String  books(@PathVariable("name") String bookname,HttpServletRequest request){
        Object o=request.getSession().getAttribute("yanzhen");
        Date date=new Date();
        long time=date.getTime();
        Object ob=request.getSession().getAttribute("yanzhen");
        bookmain bookmain=new bookmain();
       if(bookname!=null) {
//           List<book> bookmains = bookimpl.getservicevlass(bookname);
       }
      if(ob!=null && ob!=""){
          bookmain= bookmainService.getById((String )ob);
              bookmain.setBookclass(bookname);
              bookmain.setLoin(time);
              bookmainService.updateById(bookmain);
      }
        return  "books";
    }

    @RequiresPermissions("admin:book:*")
   @PostMapping("/uploadbook")
    public  String uploadbook(@RequestParam(value = "booktext")MultipartFile file
           ,@RequestParam(value = "bookname",defaultValue = "")String bookname
           ,@RequestParam(value = "bookCreate",defaultValue = "")String bookCreate){
    if(file.getSize()>0 && !bookname.equals("")&&!bookCreate.equals("")){
        bookmainService.insert_hwx_book(file,bookname,bookCreate);
        return  "index";
    }else {
        System.out.println("|bookname=" + bookname + "|bookcreate=" + bookCreate+"|");
        return "5xx";
    }
    }
















//    @GetMapping("/major")
//    @ResponseBody
//    public String  submajor(@RequestParam(value = "main") String mainname,
//                            @RequestParam("secondary") String secondaryname,
//                            HttpServletRequest request, Map map){
//        Object id=request.getSession().getAttribute("yanzhen");
//         bookmain=bookmainService.mybatis_getmajor((String) id);
//         if(bookmain==null){
//             bookmain bookmain=new bookmain();
//             bookmain.setId((String) id);
//             if(mainname!=null && mainname!=""  && secondaryname!=null && secondaryname !=""){
//                 bookmain.setMain_major(mainname);
//                 bookmain.setSecondary_major(secondaryname);
//             }
//             bookmainService.insert_major(bookmain);
//             String data=mainname+","+secondaryname;
//             return data;
//         } else if(bookmain.getMain_major()==null){
//              if(mainname!=null && mainname!="" && secondaryname!=null && secondaryname!=""){
//                  bookmain.setId((String) id);
//                  bookmain.setMain_major(mainname);
//                  bookmain.setSecondary_major(secondaryname);
//                  bookmainService.insert_major(bookmain);
//                  String data=mainname+","+secondaryname;
//                  return data;
//              }else{
//                  return "hwx请选择专业";
//              }
//         }else{
//             return "hwx你已经提交过专业，无需重复提交,修改请前往个人中心!";
//         }
//    }
}
