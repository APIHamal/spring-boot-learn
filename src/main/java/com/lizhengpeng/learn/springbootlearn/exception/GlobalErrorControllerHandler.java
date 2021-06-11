package com.lizhengpeng.learn.springbootlearn.exception;

import com.lizhengpeng.learn.springbootlearn.exception.impl.InternalServerException;
import com.lizhengpeng.learn.springbootlearn.exception.impl.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * 内部异常服务器内部异常处理
 * @author lzp
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class GlobalErrorControllerHandler extends AbstractErrorController {

    private static final Logger logger = LoggerFactory.getLogger(GlobalErrorControllerHandler.class);

    public GlobalErrorControllerHandler(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    /**
     * 返回404或者错异常提示信息
     * @param request
     * @return
     */
    @RequestMapping
    public void errorHandler(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        Map<String, Object> detailMap = getErrorAttributes(request,ErrorAttributeOptions.of(ErrorAttributeOptions.Include.values()));
        detailMap.forEach((k,v) -> {
            logger.info(String.valueOf(v));
        });
        if(status == HttpStatus.NOT_FOUND){
            throw new ResourceNotFoundException("访问的资源不存在");
        }else{
            throw new InternalServerException("服务发生错误");
        }
    }

}
