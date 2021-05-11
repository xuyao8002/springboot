package com.xuyao.springboot.controller;


import com.xuyao.springboot.bean.po.Result;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import java.util.*;

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

    protected <T> Object handleErrors(Set<ConstraintViolation<T>> validateSet) {
        List<Map> errorList = new ArrayList<>(validateSet.size());
        for (ConstraintViolation<T> validate : validateSet) {
            Map error = new HashMap();
            error.put(validate.getPropertyPath(), validate.getMessage());
            errorList.add(error);
        }
        return Result.error("校验失败", errorList);
    }

}
