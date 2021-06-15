package com.demo.Shiro;

import com.demo.Service.AccoutService;
import com.demo.pojo.Accoutuser;
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

@Component
public class myRealm extends AuthorizingRealm {

   @Autowired
    AccoutService accoutService;

   @Autowired
   Accoutuser accoutuser;
    //授权配置
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthenticationInfo=new SimpleAuthorizationInfo();
        Subject subject= SecurityUtils.getSubject();
      String id= (String) subject.getPrincipal();
        System.out.println("我进入权限认证了");
        accoutuser=accoutService.getServiceid(id);
      if(id.equals("hwxadmin")){
          System.out.println("---------------------");
          simpleAuthenticationInfo.addStringPermission("user:*:*");
          simpleAuthenticationInfo.addStringPermission("admin:*:*");
      }
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
