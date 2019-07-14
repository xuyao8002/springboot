package com.xuyao.springsession.service.impl;

import com.xuyao.springsession.exception.CustomException;
import com.xuyao.springsession.service.ICustomService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CustomServiceImpl implements ICustomService {
    @Override
    public String hello(String name) throws CustomException {
        if (StringUtils.isEmpty(name)) {
            throw new CustomException("name can't be null");
        }
        return "hello, " + name;
    }
}
