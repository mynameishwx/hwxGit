package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.pojo.Accoutuser;
import org.apache.ibatis.annotations.Mapper;

import java.awt.print.Book;
import java.util.List;

@Mapper
public interface Accoutmapper extends BaseMapper<Accoutuser> {

    public   Accoutuser getid(String id);//查询id是否已经注册


    public    Integer setacct(Accoutuser accoutuser);//注册


    public   Integer settime(String idtime,String id);//写入登录时间


    public List<Accoutuser> getIdone(Accoutuser accoutuser);//模糊查询


//    public   Integer setCondit(String condit,String id); //在线状态

    public Integer alteruser(Accoutuser accoutuser);   //修改


    List<Accoutuser> foreach(List<String> integers);  //foreach 使用方法一

    List<Accoutuser> foreachtwo (List<Accoutuser> accoutuserList);

    Integer  mydelete(Accoutuser accoutuser);
}
