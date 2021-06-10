package com.lizhengpeng.learn.springbootlearn.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 返回响应信息
 * @author lzp
 */
@Setter
@Getter
public class ResponseEntity {

    private String status;                //响应状态
    private String message;               //响应信息
    private Object affixMessage;          //附加信息
    private Object data = new Object[]{}; //响应数据

    public ResponseEntity(String status,String message){
        this.status = status;
        this.message = message;
    }

}
