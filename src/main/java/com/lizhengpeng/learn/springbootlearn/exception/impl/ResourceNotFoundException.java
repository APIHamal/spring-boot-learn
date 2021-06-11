package com.lizhengpeng.learn.springbootlearn.exception.impl;

/**
 * 资源未找到异常
 * @author lzp
 */
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
