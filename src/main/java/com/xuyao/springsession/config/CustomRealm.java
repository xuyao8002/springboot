package com.xuyao.springsession.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = getPassword(username);
        if (StringUtils.isEmpty(password)) {
            throw new UnknownAccountException("用户不存在");
        } else if(!password.equals(new String((char[])token.getCredentials()))){
            throw new IncorrectCredentialsException("密码不正确");
        }
        return new SimpleAuthenticationInfo(username, password, getName());
    }

    private String getPassword(String username){
        String password = null;
        if ("admin".equals(username)) {
            password = "admin";
        } else if ("guest".equals(username)) {
            password = "guest";
        }
        return password;
    }


    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) super.getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        roles.add("guest");
        authorizationInfo.setRoles(roles);
        roles.forEach(role -> {
            Set<String> permissions = new HashSet<>();
            if ("admin".equals(role)) {
                permissions.add("read");
                permissions.add("write");
            }else{
                permissions.add("read");
            }
            authorizationInfo.addStringPermissions(permissions);
        });
        return authorizationInfo;
    }
}