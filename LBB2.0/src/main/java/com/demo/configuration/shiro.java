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

        stringStringMap.put("/data/**","authc");

        stringStringMap.put("/user","authc");
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
    public myRealm  realm(){
        myRealm myRealm=new myRealm();

        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashIterations(1125);
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);


        return  myRealm;
    }

}
