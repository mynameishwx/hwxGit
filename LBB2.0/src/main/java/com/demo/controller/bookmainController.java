package com.demo.controller;

import com.demo.Service.bookadmin_service;
import com.demo.Service.impl.bookimpl;
import com.demo.pojo.book;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Book;

@Controller
@RequestMapping("/bookmain")
public class bookmainController{


    @Autowired
    bookadmin_service bookadmin_service;

    @Autowired
    book book;

    @Autowired
    bookimpl bookimpl;

    @GetMapping("/books")
    public  String  booksname(@RequestParam(value = "bookname") String bookname,Model model){
     if(bookname!=""){
         model.addAttribute("text",bookadmin_service.booksname(bookname));
         return  "books";
     }else
        return "5xx";
    }

    @RequiresPermissions("admin:book:*")
    @GetMapping("/admin")
    public String  bookadmin(@RequestParam(value = "pn",defaultValue = "1")Integer pn,Model model){

        return bookadmin_service.show_book(model,pn);
    }

    @RequiresPermissions("admin:book:*")
   @PostMapping("/uploadbook")
    public  String uploadbook(@RequestParam(value = "booktext")MultipartFile file
           ,@RequestParam(value = "bookname",defaultValue = "")String bookname
           ,@RequestParam(value = "bookCreate",defaultValue = "")String bookCreate
           ,@RequestParam(value = "class")String bookclass){
    if(file.getSize()>0 && !bookname.equals("")&&!bookCreate.equals("")){
        book.setBookname(bookname);
        book.setBookcreate(bookCreate);
        book.setBookclass(bookclass);
        bookimpl.insert_hwx_book(file,book);
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
