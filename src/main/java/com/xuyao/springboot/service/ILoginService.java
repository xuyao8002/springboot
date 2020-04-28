package com.xuyao.springboot.service;

public interface ILoginService {

    String login(String username, String password);

    void logout();

    String phoneLogin(String phone);

}
