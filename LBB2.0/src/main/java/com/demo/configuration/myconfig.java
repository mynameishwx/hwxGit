package com.demo.configuration;

import com.demo.comp.MyLocaleResoloer;
import com.demo.comp.myhand;
import com.demo.pojo.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class myconfig implements WebMvcConfigurer {
    @Bean
     public  WebMvcConfigurer webMvcConfigurer(){
          WebMvcConfigurer  configurer=new WebMvcConfigurer() {
              @Override
              public void addViewControllers(ViewControllerRegistry registry) {
                  registry.addViewController("/index").setViewName("index");
                  registry.addViewController("/index.html").setViewName("index");
                  registry.addViewController("/index_three").setViewName("index_three");
                  registry.addViewController("/index_two").setViewName("index_two");
                  registry.addViewController("/").setViewName("index");
                  registry.addViewController("/enter").setViewName("enter");
                  registry.addViewController("/wx").setViewName("wx");
                  registry.addViewController("/druid/**").setViewName("4xx");
                  registry.addViewController("/hwxadmin/**").setViewName("admin");
              }
          };
          return configurer;
     }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( new myhand()).addPathPatterns("/**")
                .excludePathPatterns("/*.ico","/userimg/**","/","/js/**","/css/**","/images/**","/images/userimg/**","/img/**","/index","/index_two","/enter/**")
                .excludePathPatterns("/dlindex","/zcindex")  //放行登录和注册页面
                .excludePathPatterns("/music/**")//放行视频资源
                .excludePathPatterns("/jquery/**") //放行js资源
                .excludePathPatterns("/bootstrap/**")
                .excludePathPatterns("/show");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/userimg/**").addResourceLocations("file:D:/hwxGit/LBB2.0/Data/img/userimg/");
            registry.addResourceHandler("/music/**").addResourceLocations("file:D:/hwxGit/LBB2.0/Data/music/");
        registry.addResourceHandler("/avhwx/**").addResourceLocations("file:D:/迅雷下载/snis-868-C/");
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResoloer();
    }
   @Bean
    public Accoutuser Accoutuser(){
        return  new Accoutuser();
   }

   @Bean
 public   role role(){
        return  new role();
   }

   @Bean
    public acc_role acc_role(){
        return  new acc_role();
   }

   @Bean
    public power power(){
        return new power();
   }

   @Bean
    public role_power role_power(){
        return  new role_power();
   }
}
