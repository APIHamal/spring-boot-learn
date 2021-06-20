package com.lizhengpeng.learn.springbootlearn.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@RestController
public class RedisController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private DefaultRedisScript<Boolean> defaultRedisScript;

    /**
     * 添加用户
     * @param num
     * @return
     */
    @GetMapping("/insert")
    public String insertUser(Integer num){
        for(int index = 0;index < num;index++){
            User user = new User();
            user.setName("name:"+index);
            user.setAge("age:"+index);
            user.setSex("sex:"+index);
            redisTemplate.opsForValue().set("order"+index,user);
        }
        return "添加成功";
    }

    /**
     * 获取指定的用户数据
     * @param index
     * @return
     */
    @GetMapping("/get")
    public User getUser(Integer index){
        User user = (User) redisTemplate.opsForValue().get("order"+index);
        return user;
    }

    /**
     * 执行LUA脚本
     */
    @GetMapping("/exec")
    public void execLua(HttpServletRequest request){
        String sessionId = request.getSession().getId();
        Boolean passBoolean = (Boolean) redisTemplate.execute(defaultRedisScript, Arrays.asList("COOKIE"),sessionId);
        System.out.println("LUA脚本返回结果-->"+passBoolean);
    }


}
