package com.xuyao.springsession.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class HelloController {

    @Value("${server.port}")
    Integer port;

    @GetMapping("/login")
    public String login(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        try{
            currentUser.login(token);
        }catch (Exception e){
            e.printStackTrace();
            return "login faild " + e.getMessage();
        }
        return "login access";
    }

    @GetMapping("/logout")
    public void logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }

    @GetMapping("/admin/up")
    public String admin() {
        Subject currentUser = SecurityUtils.getSubject();
        return "UP UP " + currentUser.getPrincipal();
    }

    @GetMapping("/simple/up")
    public String simple(HttpServletRequest request) {
        Subject currentUser = SecurityUtils.getSubject();
        return "UP UP " + currentUser.getPrincipal() + ", sessionId: " + request.getRequestedSessionId();
    }

    @GetMapping("/set")
    public String set(HttpSession session) {
        session.setAttribute("msg", "hello");
        return String.valueOf(port);
    }

    @GetMapping("/get")
    public String get(HttpSession session) {
        return session.getAttribute("msg") +":"+ port;
    }
}