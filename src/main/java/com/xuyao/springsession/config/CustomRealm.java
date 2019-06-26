package com.xuyao.springsession.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

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
        String password = "admin";
        return new SimpleAuthenticationInfo(username, password, getName());
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