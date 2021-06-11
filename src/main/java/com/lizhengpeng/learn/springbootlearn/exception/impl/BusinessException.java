package com.lizhengpeng.learn.springbootlearn.exception.impl;

/**
 * 系统业务异常
 * @author lzp
 */
public class BusinessException extends RuntimeException{

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String message,Throwable throwable){
        super(message,throwable);
    }

}
