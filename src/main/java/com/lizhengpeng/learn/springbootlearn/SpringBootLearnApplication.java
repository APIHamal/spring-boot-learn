package com.lizhengpeng.learn.springbootlearn;

import com.lizhengpeng.learn.springbootlearn.exception.impl.BusinessException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class SpringBootLearnApplication {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

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

    @GetMapping("/business_exception")
    public String business_exception(int type){
        try{
            if(type <= 10){
                throw new BusinessException("type参数的值不允许小于10");
            }
            return "hello:  "+type;
        }catch (Exception e){
            throw e;
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLearnApplication.class, args);
    }

}
