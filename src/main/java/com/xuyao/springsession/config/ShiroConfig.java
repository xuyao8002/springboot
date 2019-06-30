package com.xuyao.springsession.config;

import com.xuyao.springsession.utils.HashUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm() {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }
//    @Bean
//    protected CacheManager cacheManager() {
//        return new MemoryConstrainedCacheManager();
//    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        /**
         * 散列算法
         */
        hashedCredentialsMatcher.setHashAlgorithmName(HashUtils.hashAlgorithmName);
        /**
         * 散列次数
         */
        hashedCredentialsMatcher.setHashIterations(HashUtils.hashIterations);

        return hashedCredentialsMatcher;
    }


    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(Realm realm) {
        DefaultWebSecurityManager  securityManager = new DefaultWebSecurityManager ();
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置SecuritManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        //配置拦截器,实现无权限返回401,而不是跳转到登录页
        filters.put("anon", new AnonymousFilter());
        filters.put("authc", new LoginFilter());
        // 拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 过滤链定义，从上向下顺序执行，/**放在最为下边
        filterChainDefinitionMap.put("/admin/**", "roles[admin]");
        //anon:可以匿名访问
        filterChainDefinitionMap.put("/login", "anon");
        //authc:认证通过才可以访问，放在最后
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

}