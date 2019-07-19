package com.xuyao.springboot.config;

import com.xuyao.springboot.utils.HashUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
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
        }
//        else if(!password.equals(new String((char[])token.getCredentials()))){
//            throw new IncorrectCredentialsException("密码不正确");
//        }
        ByteSource credentialsSalt = getSalt(username);
        return new SimpleAuthenticationInfo(username, password, credentialsSalt, getName());
    }

    private String getPassword(String username){
        String password = null;
        ByteSource salt = getSalt(username);
        password = HashUtils.getHashVal(HashUtils.hashAlgorithmName, username, salt, HashUtils.hashIterations);
//        if ("admin".equals(username)) {
////            password = "admin";
//            //ByteSource credentialsSalt = ByteSource.Util.bytes(username);
//            password = "f6fdffe48c908deb0f4c3bd36c032e72";
//        } else if ("guest".equals(username)) {
////            password = "guest";
//            password = "fe4ceeb01d43a6c29d8f4fe93313c6c1";
//        }
        return password;
    }

    private ByteSource getSalt(String username) {
        return ByteSource.Util.bytes(username);
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) super.getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        setRolesAndPermissions(authorizationInfo, username);
        return authorizationInfo;
    }

    private void setRolesAndPermissions(SimpleAuthorizationInfo authorizationInfo, String username) {
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        if ("admin".equals(username)) {
            roles.add("admin");
            permissions.add("read");
            permissions.add("write");
        } else if ("guest".equals(username)) {
            roles.add("guest");
            permissions.add("read");
        }
        authorizationInfo.setRoles(roles);
        authorizationInfo.addStringPermissions(permissions);
    }
}