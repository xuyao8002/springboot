package com.xuyao.springboot.config;


import com.xuyao.springboot.bean.po.User;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.authc.AuthenticationToken;

@Getter
@Setter
public class PhoneToken implements AuthenticationToken {

    private String phone;

    private User user;


    @Override
    public Object getPrincipal() {
        return phone;
    }

    @Override
    public Object getCredentials() {
        return phone;
    }

    public PhoneToken(String phone) {
        this.phone = phone;
    }
}
