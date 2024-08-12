package com.xuyao.springboot.config;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class CostFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        // 过滤器初始化代码
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        log.info("{} 执行时间：{} ms", servletRequest.getRequestURI(), (System.currentTimeMillis() - start));
    }
 
    @Override
    public void destroy() {
        // 过滤器销毁代码
    }
}