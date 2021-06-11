package com.demo.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.Service.AccoutService;
import com.demo.mapper.Accoutmapper;
import com.demo.pojo.Accoutuser;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class   AccoutServiceimpl extends ServiceImpl<Accoutmapper,Accoutuser> implements AccoutService {

          @Autowired
  Accoutmapper accoutmapper;

          @Autowired
          Accoutuser accoutuser;


    //        查询
    public Accoutuser  getServiceid(String id){
//        return accoutmapper.selectById(id);
        return accoutmapper.getid(id);
    }

    @Override
    public Integer mydeletebyid(Accoutuser accoutuser) {

        return accoutmapper.mydelete(accoutuser);
    }

    //        注册
    @Transactional
    public  Integer setbyuser(Accoutuser accoutuser){
        String salt="abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(salt);
        int sjs=new Random().nextInt(stringBuffer.length()+2);
        String saltok= stringBuffer.substring(sjs-1,sjs);  //产生随即盐
        Md5Hash md5Hash=new Md5Hash(accoutuser.getPass(),salt,1125);
        accoutuser.setPass(md5Hash.toHex());
        accoutuser.setRatio(saltok);
        try {
             accoutmapper.insert(accoutuser);
        }catch (Exception e){
            System.out.println("插入失败" + accoutuser.toString());
            e.printStackTrace();
            return  0;
        }
      return  1;
//        return  accoutmapper.setacct(accoutuser);
   }


//      写入(登录时间)
    public  Integer setServiceIdtime(String idtime,String id){
        return accoutmapper.settime(idtime,id);
    }
   public List<Accoutuser> getServiceIdone(Accoutuser accoutuser){

       List<Accoutuser> accoutuserList=accoutmapper.getIdone(accoutuser);

       return accoutuserList;

   }
   //已废弃的修改在线状态方法
//      public  Integer  setServiceCondit(String condit,String id){
//          return  accoutmapper.setCondit(condit,id);
//      }

    @Override
    public Integer alterServiceuser(Accoutuser accoutuser) {
        return accoutmapper.alteruser(accoutuser);
    }
}
