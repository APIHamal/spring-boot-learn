package com.lizhengpeng.learn.springbootlearn.interceptor;

import com.lizhengpeng.learn.springbootlearn.exception.impl.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 使用Redis+LUA进行限流操作
 * @author lzp
 */
@Component
public class CurrentLimitInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DefaultRedisScript<Boolean> defaultRedisScript;

    /**
     *  默认传递的参数名称
     */
    private static final List<String> keyArray = Arrays.asList("IP");

    /**
     * LUA脚本进行限流操作
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = request.getSession().getId();
        Boolean clientPass = (Boolean) redisTemplate.execute(defaultRedisScript,keyArray,sessionId);
        if(clientPass == null || !clientPass){
            throw new BusinessException("系统监测到您当前访问频繁,请稍后在试");
        }
        return true;
    }
}
