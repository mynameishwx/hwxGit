package com.demo.configuration;

import com.demo.Shiro.myRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class shiro {

    // 创建Shirofilter
    @Bean
    public ShiroFilterFactoryBean  shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String,String> stringStringMap=new HashMap<>();
/*
  anno	不需要授权、登录就可以访问。eg:/index
authc	 需要登录授权才能访问。eg：/用户中心
authcBasic	Basic HTTP身份验证拦截器
logout	退出拦截器。退出成功后，会 redirect到设置的/URI
noSessionCreation	不创建会话连接器
perms	授权拦截器:perm['user:create']
port	端口拦截器.eg:port[80]
rest	rest风格拦截器
roles	角色拦截器。eg：role[administrator]
ssl	ssl拦截器。通过https协议才能通过
user	用户拦截器。eg：登录后（authc），第二次没登陆但是有记住我(remmbner)都可以访问
 */

        stringStringMap.put("/data/**","authc");
        stringStringMap.put("/user","authc");
        stringStringMap.put("/bookmain/**","authc");
        stringStringMap.put("/logout","logout");
        shiroFilterFactoryBean.setLoginUrl("/index");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(stringStringMap);
        return  shiroFilterFactoryBean;
    }

    //创建安全管理器
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(Realm realm){
       DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
       defaultWebSecurityManager.setRealm(realm);
        return  defaultWebSecurityManager;
    }


    @Bean
    public Realm  realm(){
        myRealm myRealm=new myRealm();

        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();

        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1125);  //散列次数
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return  myRealm;
    }

}
