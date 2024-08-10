package com.xuyao.springboot.config;

import com.xuyao.springboot.bean.po.User;
import com.xuyao.springboot.service.IUserService;
import com.xuyao.springboot.utils.CommonUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class ShiroRealm extends AuthorizingRealm {

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        ShiroToken token = (ShiroToken) authenticationToken;
        String username = token.getUsername();
        User query = new User();
        query.setUsername(username);
        User user = AppContextAware.getBean(IUserService.class).selectOne(query);
        if (user == null) {
            throw new UnknownAccountException(username + "不存在");
        }
        String password = user.getPassword();
        user.setPassword(null);
        token.setUser(user);
        return new SimpleAuthenticationInfo(username, password, CommonUtils.getSalt(username), getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        String username = (String) super.getAvailablePrincipal(principalCollection);
////        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
////        setRolesAndPermissions(authorizationInfo, username);
////        return authorizationInfo;
        return null;
    }

//    private void setRolesAndPermissions(SimpleAuthorizationInfo authorizationInfo, String username) {
//        Set<String> roles = new HashSet<>();
//        Set<String> permissions = new HashSet<>();
//        if ("admin".equals(username)) {
//            roles.add("admin");
//            permissions.add("read");
//            permissions.add("write");
//        } else {
//            roles.add("guest");
//            permissions.add("read");
//        }
//        authorizationInfo.setRoles(roles);
//        authorizationInfo.addStringPermissions(permissions);
//    }
}