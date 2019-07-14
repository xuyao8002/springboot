package com.xuyao.springsession.config;

import com.xuyao.springsession.exception.CustomException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomExceptionHandler {
   //自定义的异常
   @ExceptionHandler(CustomException.class)
   @ResponseBody
   private Object customHandler(CustomException e){
      e.printStackTrace();
      return "CustomExceptionHandler.CustomException: " + e.getMessage();
   }
   //其他异常
   @ExceptionHandler(Exception.class)
   @ResponseBody
   public Object exceptionHandler(Exception e){
      e.printStackTrace();
      return "CustomExceptionHandler.Exception: " + e.getMessage();
   }
}