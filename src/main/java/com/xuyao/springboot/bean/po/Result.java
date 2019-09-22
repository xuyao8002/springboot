package com.xuyao.springboot.bean.po;

import com.xuyao.springboot.consts.Consts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Result {

    private int code;

    private String message;

    private Object data;

    public static Result error(String message){
        return new Result(Consts.DEFAULT_ERROR_CDOE, message, null);
    }

    public static Result error(String message, Object data){
        return new Result(Consts.DEFAULT_ERROR_CDOE, message, data);
    }

    public static Result error(int errorCode, String message, Object data){
        return new Result(errorCode, message, data);
    }

    public static Result success(String message){
        return new Result(Consts.DEFAULT_SUCCESS_CDOE, message, null);
    }

    public static Result success(String message, Object data){
        return new Result(Consts.DEFAULT_SUCCESS_CDOE, message, data);
    }

    public static Result success(int successCode, String message, Object data){
        return new Result(successCode, message, data);
    }
}
