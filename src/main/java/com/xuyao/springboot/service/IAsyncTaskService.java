package com.xuyao.springboot.service;


public interface IAsyncTaskService {

    void sendSms() throws InterruptedException;

    void sendEmail() throws InterruptedException;
}
