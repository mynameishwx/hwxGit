package com.demo.Shiro;

import com.demo.Service.AccoutService;
import com.demo.Service.acc_roleService;
import com.demo.Service.powerService;
import com.demo.Service.role_powerService;
import com.demo.pojo.Accoutuser;
import com.demo.pojo.acc_role;
import com.demo.pojo.power;
import com.demo.pojo.role_power;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class myRealm extends AuthorizingRealm {

   @Autowired
    AccoutService accoutService;

   @Autowired
    acc_roleService roleService;

   @Autowired
   acc_role acc_role;

   @Autowired
   com.demo.Service.role_powerService role_powerService;

   @Autowired
   Accoutuser accoutuser;

   @Autowired
    role_power power;

   @Autowired
    com.demo.pojo.power pojipower;

   @Autowired
    powerService powerService;
    //授权配置
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthenticationInfo=new SimpleAuthorizationInfo();
        Subject subject= SecurityUtils.getSubject();
      String id= (String) subject.getPrincipal();
        System.out.println("我进入权限认证了");
        accoutuser=accoutService.getServiceid(id);
      List<acc_role> acc_roles=new ArrayList<>();
        acc_role.setAccId(id);
      acc_roles=roleService.getbyname_hwx(acc_role);
      Iterator<acc_role> iterator_acc_role=acc_roles.iterator();
      while (iterator_acc_role.hasNext()){
          acc_role=iterator_acc_role.next();
          if(acc_role.getRoleId()!=null){
              power.setRoleId(acc_role.getRoleId());
              List<role_power> role_powers=role_powerService.getbyrole_hwx(power);
              Iterator<role_power> iterator=role_powers.iterator();
              while (iterator.hasNext()){
                  power=iterator.next();
                  pojipower=powerService.getbyid_hwx(power.getPowerId());
                  simpleAuthenticationInfo.addStringPermission(pojipower.getPowerUrl());
                  System.out.println("用户:" + id + "成功添加" + pojipower.getPowerUrl() + "权限");
              }
          }else {
              System.out.println("acc_role的RoleId为空");
          }
      }

//      if(id.equals("hwxadmin")){
//          System.out.println("---------------------");
//          simpleAuthenticationInfo.addStringPermission("user:*:*");
//          simpleAuthenticationInfo.addStringPermission("admin:*:*");
//      }
        return simpleAuthenticationInfo;
    }


    //认证配置
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
     UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        String name=token.getUsername();
        accoutuser=accoutService.getServiceid(name);
        return new SimpleAuthenticationInfo(
                 name,      //id
                accoutuser.getPass(),     //密码
                ByteSource.Util.bytes(accoutuser.getRatio()), //盐
                getName());
    }
}
