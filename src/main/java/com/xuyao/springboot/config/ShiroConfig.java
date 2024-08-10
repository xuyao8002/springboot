package com.xuyao.springboot.config;


import com.xuyao.springboot.utils.CommonUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Value("${shiro.filter}")
    private String shiroFilter;

    @Bean
    public Realm realm() {
        ShiroRealm customRealm = new ShiroRealm();
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }

    @Bean
    public PhoneRealm phoneRealm() {
        return new PhoneRealm();
    }

//    @Bean
//    protected CacheManager cacheManager() {
//        return new MemoryConstrainedCacheManager();
//    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(CommonUtils.hashAlgorithmName);
        hashedCredentialsMatcher.setHashIterations(CommonUtils.hashIterations);
        return hashedCredentialsMatcher;
    }

//    @Bean
//    public SessionManager sessionManager() {
//
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
//        //配置监听
//        listeners.add(sessionListener());
//        sessionManager.setSessionListeners(listeners);
//        sessionManager.setSessionIdCookie(sessionIdCookie());
//        sessionManager.setSessionDAO(sessionDAO());
//        sessionManager.setCacheManager(cacheManager());
//
//        //全局会话超时时间（单位毫秒），默认30分钟
//        sessionManager.setGlobalSessionTimeout(1800000);
//        //是否开启删除无效的session对象  默认为true
//        sessionManager.setDeleteInvalidSessions(true);
//        //是否开启定时调度器进行检测过期session 默认为true
//        sessionManager.setSessionValidationSchedulerEnabled(true);
//        //设置session失效的扫描时间, 清理用户直接关闭浏览器造成的孤立会话 默认为 1个小时
//        //设置该属性 就不需要设置 ExecutorServiceSessionValidationScheduler 底层也是默认自动调用ExecutorServiceSessionValidationScheduler
//        //设置为60秒
//        sessionManager.setSessionValidationInterval(1000 * 60);
//
//        //取消url 后面的 JSESSIONID
//        sessionManager.setSessionIdUrlRewritingEnabled(false);
//
//        return sessionManager;
//
//    }

//    @Bean
//    public SessionDAO sessionDAO() {
//        EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
//        //使用ehCacheManager
//        enterpriseCacheSessionDAO.setCacheManager(cacheManager());
//        //设置session缓存的名字 默认为 shiro-activeSessionCache
//        enterpriseCacheSessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
//        //sessionId生成器
//        enterpriseCacheSessionDAO.setSessionIdGenerator(sessionIdGenerator());
//        return enterpriseCacheSessionDAO;
//    }



//    @Bean
//    public ShiroSessionListener sessionListener(){
//        ShiroSessionListener sessionListener = new ShiroSessionListener();
//        return sessionListener;
//    }

//    @Bean
//    public SimpleCookie sessionIdCookie(){
//        //这个参数是cookie的名称
//        SimpleCookie simpleCookie = new SimpleCookie("sid");
//        //setcookie的httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：
//
//        //setcookie()的第七个参数
//        //设为true后，只能通过http访问，javascript无法访问
//        //防止xss读取cookie
//        simpleCookie.setHttpOnly(true);
//        simpleCookie.setPath("/");
//        //maxAge=-1表示浏览器关闭时失效此Cookie
//        simpleCookie.setMaxAge(-1);
//        return simpleCookie;
//    }

    @Bean
    public SessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(Realm realm, PhoneRealm phoneRealm) {
        DefaultWebSecurityManager  securityManager = new DefaultWebSecurityManager ();
        List<Realm> realms = new ArrayList<>();
        realms.add(realm);
        realms.add(phoneRealm);
        securityManager.setRealms(realms);
//        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("anon", new AnonymousFilter());
        filters.put("authc", new ShiroAuthFilter());

//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//////        filterChainDefinitionMap.put("/admin/**", "roles[admin]");
////        filterChainDefinitionMap.put("/login", "anon");
////        filterChainDefinitionMap.put("/**", "authc");
////        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        shiroFilterFactoryBean.setFilterChainDefinitions(shiroFilter);

        return shiroFilterFactoryBean;
    }

}