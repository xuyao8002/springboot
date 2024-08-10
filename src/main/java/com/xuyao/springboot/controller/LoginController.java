package com.xuyao.springboot.controller;


import com.xuyao.springboot.bean.po.Result;
import com.xuyao.springboot.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @PostMapping("/login")
    public Object login(String username, String password){
        try{
            String tokenId = loginService.login(username, password);
            return Result.success("登录成功", tokenId);
        }catch (UnknownAccountException e){
            log.error("用户不存在", e);
            return Result.error("用户不存在");
        }catch (IncorrectCredentialsException e){
            log.error("密码错误，密码{}", password, e);
            return Result.error("密码错误");
        }
    }

    @PostMapping("/logout")
    public Object logout(){
        loginService.logout();
        return Result.success("退出成功");
    }

    @PostMapping("/phoneLogin")
    public Object phoneLogin(String phone){
        try{
            String tokenId = loginService.phoneLogin(phone);
            return Result.success("登录成功", tokenId);
        }catch (UnknownAccountException e){
            log.error("手机号不存在", e);
            return Result.error("手机号不存在");
        }
    }

    @RequestMapping("/log")
    @ResponseBody
    public Object log() {
        String traceId = System.currentTimeMillis() + "";
        MDC.put("traceId", traceId);
        log.info("log in main thread: ");
        Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
        new Thread(() -> {
            MDC.setContextMap(copyOfContextMap);
            log.info("log in new thread: ");
            MDC.clear();
        }).start();
        MDC.remove("traceId");
        return traceId;
    }

}
