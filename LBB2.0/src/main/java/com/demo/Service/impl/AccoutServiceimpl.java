package com.demo.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.Service.AccoutService;
import com.demo.mapper.Accoutmapper;
import com.demo.pojo.Accoutuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class   AccoutServiceimpl extends ServiceImpl<Accoutmapper,Accoutuser> implements AccoutService {
          @Lazy
          @Autowired
  Accoutmapper accoutmapper;



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
        return accoutmapper.insert(accoutuser);
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
