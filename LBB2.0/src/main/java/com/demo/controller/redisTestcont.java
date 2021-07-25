package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

@Controller
public class redisTestcont {
    @Autowired
    RedisTemplate redisTemplate;


    /*
    天
    TimeUnit.DAYS;

     小时
    TimeUnit.HOURS;

     分钟
    TimeUnit.MINUTES;

     秒
    TimeUnit.SECONDS;

     毫秒
    TimeUnit.MILLISECONDS;

    */
    @ResponseBody
    @RequestMapping("/redis")
    public  String  redisTest(){
       try {
           redisTemplate.opsForValue().set("hs","奥里给");
           System.out.println(redisTemplate.opsForValue().get("hs"));
           redisTemplate.opsForValue().set("hs","奥里给b");
           System.out.println(redisTemplate.opsForValue().get("hs"));
       }catch (Exception e){
           e.printStackTrace();
           return "失败";
       }
        return  "成功!";
    }
}
