package com.xuyao.springsession.service;

import com.xuyao.springsession.exception.CustomException;

public interface ICustomService {

    String hello(String name) throws CustomException;

}
