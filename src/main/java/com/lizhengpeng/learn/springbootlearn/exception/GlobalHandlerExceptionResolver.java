package com.lizhengpeng.learn.springbootlearn.exception;

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
        logger.info("系统监测到异常发生",e);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(new ResponseEntity("fail","接口调用失败"));
        modelAndView.setView(new MappingJackson2JsonView());
        return modelAndView;
    }
}
