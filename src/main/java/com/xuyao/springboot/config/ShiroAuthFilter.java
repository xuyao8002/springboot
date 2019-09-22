package com.xuyao.springboot.config;


import com.xuyao.springboot.bean.po.Result;
import com.xuyao.springboot.consts.Consts;
import com.xuyao.springboot.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ShiroAuthFilter extends FormAuthenticationFilter {

    private RedisTemplate<String, Object> redisTemplate;

    public ShiroAuthFilter(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Result result = Result.error("tokenId不存在");
        CommonUtils.printJSON(result, (HttpServletResponse) response);
        return false;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String tokenId = CommonUtils.getTokenId((HttpServletRequest) request);
        boolean isAccessAllowed = StringUtils.isNotBlank(tokenId) && redisTemplate.hasKey(tokenId);
        if(isAccessAllowed){
            redisTemplate.expire(tokenId, Consts.TOKEN_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        }
        return isAccessAllowed;
    }

}
