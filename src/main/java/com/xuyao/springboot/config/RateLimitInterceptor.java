package com.xuyao.springboot.config;

import com.xuyao.springboot.annotation.RateLimit;
import com.xuyao.springboot.bean.po.Result;
import com.xuyao.springboot.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * API请求速率限制拦截器
 */
@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            RateLimit accessLimit = hm.getMethodAnnotation(RateLimit.class);
            if (accessLimit == null) {
                return true;
            }
            String key = CommonUtils.getIpAddr(request) + request.getContextPath() + ":" + request.getServletPath() ;
            Integer current = (Integer) redisTemplate.opsForValue().get(key);
            int seconds = accessLimit.seconds();
            int limit = accessLimit.limit();
            System.out.println(String.format("当前值，limit:%s,current:%s", limit, current));
            //不存在时初始为1
            if (current == null) {
                redisTemplate.opsForValue().setIfAbsent(key, 1, seconds, TimeUnit.SECONDS);
                return true;
            }
            //未达到上限时递增1
            if (current < limit) {
                redisTemplate.opsForValue().increment(key);
                return true;
            }else{
                Result result = Result.error("访问限制");
                CommonUtils.printJSON(result, response);
                return false;
            }
        }
        return true;
    }
}