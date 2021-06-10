package com.lizhengpeng.learn.springbootlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootLearnApplication {

    /**
     * 该方法异常会走入GlobalHandlerExceptionResolver
     * @param desc
     * @return
     */
    @GetMapping("/hello")
    public String sayHello(String desc){
        if(desc == null || desc.trim().length() == 0){
            throw new IllegalArgumentException("参数传递异常");
        }
        return "hello:  "+desc;
    }

    /**
     * 参数赋值等异常或servlet容器自身异常会走入/error路径
     * @param type
     * @return
     */
    @GetMapping("/errors")
    public String print(int type){
        return "接收到参数:  "+String.valueOf(type);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLearnApplication.class, args);
    }

}
