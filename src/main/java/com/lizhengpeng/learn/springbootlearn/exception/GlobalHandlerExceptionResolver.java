package com.lizhengpeng.learn.springbootlearn.exception;

import com.lizhengpeng.learn.springbootlearn.exception.impl.BusinessException;
import com.lizhengpeng.learn.springbootlearn.exception.impl.InternalServerException;
import com.lizhengpeng.learn.springbootlearn.exception.impl.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 配置处/error路径外的错误处理器
 * @author lzp
 */
@Component
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(GlobalHandlerExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        logger.info("GlobalHandlerExceptionResolver拦截到异常",e);
        ModelAndView modelAndView = new ModelAndView();
        if(clientAcceptHTML(httpServletRequest)){
            if(e instanceof ResourceNotFoundException){
                modelAndView.setViewName("404");
            }else if(e instanceof BusinessException){
                modelAndView.setViewName("business_error");
            }else{
                modelAndView.setViewName("error");
            }
        }else{
            modelAndView.setView(new MappingJackson2JsonView());
            modelAndView.addObject("status","fail");
            if(e instanceof ResourceNotFoundException){
                modelAndView.addObject("message","访问的资源不存在");
            }else if(e instanceof BusinessException){
                modelAndView.addObject("message",e.getMessage());
            }else{
                modelAndView.addObject("message","服务器内部发生错误");
            }
        }
        return modelAndView;
    }

    /**
     * 判断远程客户端是否支持HTML
     * 若不支持html则统一返回JSON数据
     * @param request
     * @return
     */
    private boolean clientAcceptHTML(HttpServletRequest request){
        String acceptText = request.getHeader("Accept") != null ? request.getHeader("Accept") : request.getHeader("accept");
        if(acceptText == null){
            return false;
        }
        return acceptText.indexOf("text/html") != -1;
    }

}
