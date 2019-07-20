package com.xuyao.springboot.service;

import com.xuyao.springboot.exception.CustomException;

public interface ICustomService {

    String hello(String name) throws CustomException;

    void stopWatch() throws InterruptedException;

}
