package com.eason.order.exception;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class GlobalExceptionHandler {  // 全局异常处理器

    @ExceptionHandler(Throwable.class)
    public String error(){
        return "error";
    }
}
