package com.xuyao.springboot.service.impl;


import com.xuyao.springboot.config.PhoneToken;
import com.xuyao.springboot.config.ShiroToken;
import com.xuyao.springboot.consts.Consts;
import com.xuyao.springboot.service.ILoginService;
import com.xuyao.springboot.utils.CommonUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String login(String username, String password) {
        ShiroToken token = new ShiroToken(username, password);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
        String tokenId = DigestUtils.md5DigestAsHex((UUID.randomUUID().toString()).getBytes());
        redisTemplate.opsForValue().set(tokenId,  token.getUser(), Consts.TOKEN_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        return tokenId;
    }

    @Override
    public void logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        String tokenId = CommonUtils.getTokenId(request);
        redisTemplate.delete(tokenId);
    }

    @Override
    public String phoneLogin(String phone) {
        PhoneToken token = new PhoneToken(phone);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
        String tokenId = DigestUtils.md5DigestAsHex((UUID.randomUUID().toString()).getBytes());
        redisTemplate.opsForValue().set(tokenId,  token.getUser(), Consts.TOKEN_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        return tokenId;
    }
}
