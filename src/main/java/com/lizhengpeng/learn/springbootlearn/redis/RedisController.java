package com.lizhengpeng.learn.springbootlearn.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

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


}
