package com.xuyao.springboot.config;

import com.xuyao.springboot.bean.po.User;
import com.xuyao.springboot.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class PhoneRealm extends AuthorizingRealm {

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        PhoneToken token = (PhoneToken) authenticationToken;
        String phone = token.getPhone();
        User query = new User();
        query.setPhone(phone);
        User user = AppContextAware.getBean(IUserService.class).selectOne(query);
        if (user == null) {
            throw new UnknownAccountException("手机号" + phone + "不存在");
        }
        user.setPassword(null);
        token.setUser(user);
        return new SimpleAuthenticationInfo(phone, phone, getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token.getClass().isAssignableFrom(PhoneToken.class);
    }
}