package com.xuyao.springboot.controller;


import com.xuyao.springboot.bean.po.Result;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController {

    protected Object handleErrors(BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<Map> errors = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            Map errorMap = new HashMap();
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            errors.add(errorMap);
        }
        return Result.error("校验失败", errors);
    }

}
