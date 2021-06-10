package com.lizhengpeng.learn.springbootlearn.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

/**
 * 内部异常服务器内部异常处理
 * @author lzp
 */
@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class GlobalErrorControllerHandler extends AbstractErrorController {

    private static final Logger logger = LoggerFactory.getLogger(GlobalErrorControllerHandler.class);

    public GlobalErrorControllerHandler(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @GetMapping
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response){
        HttpStatus status = getStatus(request);
        Map<String, Object> detailMap = getErrorAttributes(request,ErrorAttributeOptions.of(ErrorAttributeOptions.Include.values()));
        detailMap.forEach((k,v) -> {
            if(v instanceof Throwable){
                logger.info("exception:"+k,(Throwable)v);
            }else{
                logger.info(String.valueOf(v));
            }
        });
        return new ResponseEntity("fail","检测到异常发生");
    }

}
