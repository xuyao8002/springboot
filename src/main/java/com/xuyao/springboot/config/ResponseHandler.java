package com.xuyao.springboot.config;

import com.xuyao.springboot.bean.po.Result;
import com.xuyao.springboot.exception.CustomException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;

@RestControllerAdvice
public class ResponseHandler implements ResponseBodyAdvice {
   @Override
   public boolean supports(MethodParameter methodParameter, Class aClass) {
           Annotation[] methodAnnotations = methodParameter.getMethodAnnotations();
           if(methodAnnotations != null && methodAnnotations.length > 0){
               for (Annotation methodAnnotation : methodAnnotations) {
                   if(methodAnnotation.annotationType().equals(WrapResponse.class)){
                       return true;
                   }
               }
           }
       return false;
   }

   @Override
   public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
       return Result.success("success", o);
   }


    //自定义的异常
    @ExceptionHandler(CustomException.class)
    private Object customHandler(CustomException e){
        e.printStackTrace();
        return Result.error("异常", e.getMessage());
    }
    //其他异常
    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(Exception e){
        e.printStackTrace();
        return Result.error("异常", "处理失败");
    }
}