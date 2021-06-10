package com.demo.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.pojo.Accoutuser;

import java.util.List;

public interface AccoutService extends IService<Accoutuser> {
    public Accoutuser getServiceid(String id);

    public  Integer setbyuser(Accoutuser accoutuser);

    public  Integer setServiceIdtime(String idtime,String id);

    public List<Accoutuser> getServiceIdone(Accoutuser accoutuser);

//    public  Integer setServiceCondit(String condit,String id);

    public  Integer  alterServiceuser(Accoutuser accoutuser);

    public Integer mydeletebyid(Accoutuser accoutuser);
}
