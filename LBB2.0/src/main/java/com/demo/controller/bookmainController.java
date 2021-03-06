package com.demo.controller;

import com.demo.Service.bookService;
import com.demo.Service.bookmainService;
import com.demo.Service.impl.bookimpl;
import com.demo.Service.impl.bookmainimpl;
import com.demo.comp.bookdate;
import com.demo.pojo.book;
import com.demo.pojo.bookmain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
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

    @GetMapping("/uploadbook")
    @ResponseBody
    public  String uploadbook(@RequestParam("bookfile")MultipartFile file){
        try {
            byte[] bytes=new byte[1024*1024];
            ByteBuffer byteBuffer=ByteBuffer.allocate(1024*1024);
            byteBuffer.put(file.getBytes());
            byteBuffer.flip();
            bytes=new byte[1024*1024];
            byteBuffer.get(bytes);
            String hh=new String(bytes,0,byteBuffer.limit());
            System.out.println(hh);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "ok";
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
//                  return "hwx???????????????";
//              }
//         }else{
//             return "hwx?????????????????????????????????????????????,???????????????????????????!";
//         }
//    }
}
