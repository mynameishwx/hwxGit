package com.demo.configuration;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = "com.demo.mapper")
@Configuration
public class myBatisConfig {
//    @Bean
//    public MybatisPlusInterceptor mybatisPlusInterceptor() {
//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
//        return interceptor;
//    }

    @Bean
    public MybatisPlusInterceptor  mybatisPlusInterceptor(){

        MybatisPlusInterceptor  mybatisPlusInterceptor=new MybatisPlusInterceptor();

        //配置乐观锁
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        PaginationInnerInterceptor paginationInnerInterceptor =new PaginationInnerInterceptor();


        //配置分页显示条数
        paginationInnerInterceptor.setMaxLimit(5L);
       mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);
       return  mybatisPlusInterceptor;
    }
}
