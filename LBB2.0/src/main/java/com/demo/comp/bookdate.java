package com.demo.comp;

import com.demo.Service.bookmainService;
import com.demo.mapper.bookmainmapper;
import com.demo.pojo.bookmain;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Data
@Component
public class bookdate implements HttpSessionListener {
    private  static String name;
    private  HttpServletRequest request;

    private  static  String  booksession;
    private  static  int jishu=0;
    private static String[]  createsession=new  String[10];

    private  String  deletesession;
    private  static  bookmainmapper bookmainmapper;


    private  static  bookmainService bookmainService;

   @Autowired
   public void  setBookmainmapper(bookmainmapper bookmainmapper){
       bookdate.bookmainmapper=bookmainmapper;
   }

   @Autowired
   public  void setBookmainService(bookmainService bookmainService){
       bookdate.bookmainService=bookmainService;
   }
    public bookdate() {
    }


    /*
    5.20:
        bookdate解读
        这个类主要作用是通过session来判定用户是否退出,
        sessionCreated :这个类的是最先生效的，把新的session存入到createsession中，
        登录后，首先会到myhand中，(到这里时并不会开始算时间，因为是图书馆管理系统
        ，所以到阅读室(bookmaincontroller)才开始算时间)


        因为创建账号时会产生三个session，每当session销毁时，sessionDestroyed 这个类会消除空的session，
        当只有一个session时，把这个session放到bookdate的静态booksession中，然后把booksession放到这个账号的session中,
        当没有session时，会把账号的bookclass置为”NO“,并算剩余看书时间，把当前session销毁时间记录



        当重新登录时，由于上一次session注销时已经结算了剩余时间，第一次进入bookmaincontroller相当于重新纪录登录时间，

5/20号测试到  jishu等于一时,结果是(会纪录session)ok的，明天测试看看是不是等于零时会自动结算时间

     5.21:




     */

    public  boolean  pollinguser(String name,HttpServletRequest request){

        System.out.println("sdsdjsd");
        bookmain bookmain=new bookmain();
        Date date=new Date();
       if(bookmainmapper!=null && name!=null){
           bookmain=bookmainmapper.getById(name);
           if(bookmain!=null){
               if(bookmain.getBookclass()!="NO"){
                   Long loin = bookmain.getLoin();
                  if(loin!=null){
                      date =new Date();
                      long xz=date.getTime();
                      int x= (int) ((xz-loin)/1000);
                      float ok=0;
                      if(x<60){
                          x=60;
                      }
                      ok=x/60;
                      System.out.println("已使用"+ok+"分钟"+"-------过去"+x+"秒");
                      float show=bookmain.getShowTime();
                      if(ok>show){
                          bookmain.setShowTime(0);
                          bookmainService.updateById(bookmain);
                          return  false;
                      }else {
                          bookmain.setShowTime((int) (show-ok));
                          bookmainService.updateById(bookmain);
                          return true;
                      }
                  }else return  true;
               }
           }
       }
       return  true;
    }


    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
       String  uu= arg0.getSession().getId();
      if(uu!=null){
          createsession[jishu]=uu;
          ++jishu;
      }
        System.out.println(createsession);
        System.out.println("Session创建+" + uu+":"+arg0.toString()+"name->"+name+"<-");
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {

       String tt=arg0.getSession().getId();

       this.deletesession=tt;

      if(jishu>1){
          for(int t=0;t<10;t++){
              if(createsession[t]==tt){
                  createsession[t]=null;
                  --jishu;
              }
          }
      } else if(jishu==1) {
           for (int x = 0; x < 10; x++) {
               if (createsession[x] != null) {
                   booksession = createsession[x];
                   --jishu;
               }
           }
           if(booksession!=null && name!=null){

               bookmain bookmain=new bookmain();
               bookmain.setId(name);
               bookmain.setSession(booksession);
               bookmainService.updateById(bookmain);
           }
       }

        if(jishu==0){
            if(booksession!=null) {
                bookmain bookmain=new bookmain();
                List<bookmain> bookmains = bookmainService.getbysessions(booksession);
                Iterator<bookmain> iterator = bookmains.iterator();
                while (iterator.hasNext()) {
                    bookmain = iterator.next();
                    Date date = new Date();
                  if(bookmain.getBookclass()!="NO"){
                      long  loin= bookmain.getLoin();
                      if(loin!=0){
                          date =new Date();
                          long xz=date.getTime();
                          int x= (int) ((xz-loin)/1000);
                          float ok=0;
                          if(x<60){
                              x=60;
                          }
                          ok=x/60;
                          System.out.println("session已使用"+ok+"分钟"+"-------过去"+x+"秒");
                          float show=bookmain.getShowTime();
                          if(ok>show){
                              bookmain.setShowTime(0);
                              bookmain.setBookclass("NO");
                              bookmainService.updateById(bookmain);
                          }else {
                              bookmain.setShowTime((int) (show-ok));
                              bookmain.setLoin(xz);
                              bookmain.setBookclass("NO");
                              bookmainService.updateById(bookmain);
                          }
                      }
                  }
//
                }
            }else System.out.println("booksession为空");
        }
        System.out.println("session销毁" + tt);
    }


}
