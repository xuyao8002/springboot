package com.xuyao.springboot.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.realm.Realm;

public class ThrowExceptionStrategy extends AtLeastOneSuccessfulStrategy {

    @Override
    public AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo singleRealmInfo, AuthenticationInfo aggregateInfo, Throwable t) throws AuthenticationException {
        if(t instanceof AuthenticationException){
            throw (AuthenticationException)t;
        }
        return super.afterAttempt(realm, token, singleRealmInfo, aggregateInfo, t);
    }

}