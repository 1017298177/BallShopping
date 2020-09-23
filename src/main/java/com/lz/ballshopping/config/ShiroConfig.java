package com.lz.ballshopping.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Component
public class ShiroConfig {

    @Autowired
    private UserRealm userRealm;


    @Bean
    public UserFilter frontUserFilter() {
        UserFilter userFilter = new UserFilter();
        userFilter.setLoginUrl("/shopping/login");
        return userFilter;
    }

    //2.DefaultWebSecurityManager 管理对象
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setRememberMeManager(rememberMeManager());
        securityManager.setSessionManager(sessionManager());
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }


    //3.ShiroFilterFactoryBean 安全管理
    @Bean/*("shiroFilter")*/
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);
        Map<String, Filter> filters = new HashMap<>();
        filters.put("frontUserFilter", frontUserFilter());
        bean.setFilters(filters);
        //设置过滤器
        bean.setLoginUrl("/account/login");
        bean.setSuccessUrl("/account/index");
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/static/**", "anon");
        filterMap.put("/assets/**", "anon");
        filterMap.put("/css/**", "anon");
        filterMap.put("/font/**", "anon");
        filterMap.put("/images/**", "anon");
        filterMap.put("/js/**", "anon");
        filterMap.put("/plugin/**", "anon");
        filterMap.put("/products/**", "anon");
        filterMap.put("/Widget/**", "anon");
        filterMap.put("/shop/**", "anon");
        filterMap.put("/account/login", "anon");
        filterMap.put("/api/basketBall/**", "frontUserFilter");
        filterMap.put("/shopping/productSale", "frontUserFilter");
        filterMap.put("/shopping/shoppingCart", "frontUserFilter");
        filterMap.put("/shopping/productPay", "frontUserFilter");
        filterMap.put("/shopping/profile/**", "frontUserFilter");
        filterMap.put("/account/**", "user");
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }


    @Bean
    public UserRealm getUserRealm(@Qualifier("credentialsMatcher") HashedCredentialsMatcher credentialsMatcher) {
        //设置加密方式
        userRealm.setCredentialsMatcher(credentialsMatcher);
        return userRealm;
    }

    //设置加密
    @Bean
    public HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher matcher =
                new HashedCredentialsMatcher();
        //设置属性值
        //设置加密算法
        matcher.setHashAlgorithmName("MD5");
        matcher.setStoredCredentialsHexEncoded(true);
        //设置加密次数
        matcher.setHashIterations(1024);
        return matcher;
    }

    //整合shiro和thymeleaf
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

    /**
     * 自动代理类，支持Shiro的注解
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator =
                new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启Shiro的注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(getDefaultWebSecurityManager());
        return authorizationAttributeSourceAdvisor;
    }


    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        byte[] cipherKey = Base64.decode("wGiHplamyXlVB11UXWol8g==");
        cookieRememberMeManager.setCipherService(new AesCipherService());
        cookieRememberMeManager.setCipherKey(cipherKey);
        return cookieRememberMeManager;
    }


    @Bean
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe  
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //如果httyOnly设置为true，则客户端不会暴露给客户端脚本代码，    
        //使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击；    
        simpleCookie.setHttpOnly(true);
        //      //记住我cookie生效时间,单位是秒     
        simpleCookie.setMaxAge(1*24*60*60);
        return simpleCookie;
    }

    @Bean
    public SimpleCookie sessionCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("shiro.session");
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(1*24*60*60);
        return simpleCookie;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionIdCookie(sessionCookie());
        return sessionManager;
    }


}
