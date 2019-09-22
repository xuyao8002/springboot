package com.xuyao.springboot.config;


import com.xuyao.springboot.bean.po.User;
import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

@Data
public class ShiroToken extends UsernamePasswordToken {

    private User user;

    public ShiroToken(String username, String password){
        super(username, password);
    }

}
