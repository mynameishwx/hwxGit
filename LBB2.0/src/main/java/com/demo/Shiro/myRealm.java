package com.demo.Shiro;

import com.demo.pojo.Accoutuser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class myRealm extends AuthorizingRealm {


    //授权配置
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }


    //认证配置
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Accoutuser upuser= (Accoutuser) authenticationToken;
        if(upuser.getId().equals("hwx")){
             throw new UnknownAccountException("用户错误");
        }
        return new SimpleAuthenticationInfo(upuser.getId(),upuser.getPass(),"ShiroReaml");
    }
}
