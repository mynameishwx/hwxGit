package com.demo.comp;

import com.alibaba.druid.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
public class myhand implements HandlerInterceptor {
    private  static RedisTemplate redisTemplate;
    @Autowired
   public void setRedisTemplate(RedisTemplate redisTemplate){
        myhand.redisTemplate=redisTemplate;
    }
//   这个方法在controller前执行，用来拦截请求
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object  user=request.getSession().getAttribute("yanzhen");
         if(user!=null){
//           boolean tt=true;
             if (redisTemplate.opsForValue().get((String) user) == null) {
                 request.setAttribute("tishi","请先登录");
                 response.sendRedirect("/enter");
//             request.getRequestDispatcher("/enter").forward(request,response);
                 return  false;
             } else {
                 redisTemplate.opsForValue().set((String) user, 1, 1, TimeUnit.MINUTES);
             }
//           tt=new bookdate().pollinguser((String) user,request);
             return  true;
         }
         else
         {
             request.setAttribute("tishi","请先登录");
             response.sendRedirect("/enter");
//             request.getRequestDispatcher("/enter").forward(request,response);
             return  false;
         }
    }

    /*
      这个方法只会在当前这个Interceptor的preHandle方法返回值为true
      且在controller 执行结束后的时候才会执行,但是他在视图渲染之前结束
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            String  patg=  request.getServletPath();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
//    查询请求的IP地址 ,和拦截器没有关系的类，暂时存放
    public  String getIpAddress(HttpServletRequest request) {

        String sourceIp = null;

        String ipAddresses = request.getHeader("x-forwarded-for");

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getRemoteAddr();
        }
        if (!StringUtils.isEmpty(ipAddresses)) {
            sourceIp = ipAddresses.split(",")[0];
        }

        return sourceIp;
    }

}
