package com.lizhengpeng.learn.springbootlearn.exception.impl;

/**
 * 服务内部异常
 * @author lzp
 */
public class InternalServerException extends RuntimeException{
    public InternalServerException(String message){
        super(message);
    }
}
