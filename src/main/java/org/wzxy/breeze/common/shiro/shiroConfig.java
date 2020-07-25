package org.wzxy.breeze.common.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.common.model.entity.Encryption;
import org.wzxy.breeze.common.shiro.filter.permissionFilter;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 覃能健
 * @create 2020-04
 */
@Configuration
public class shiroConfig {

    //不加这个注解不生效，具体不详
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return  defaultAAP;
    }

    /**
     * 凭证匹配器
     * 密码校验交给Shiro的SimpleAuthenticationInfo进行处理
     */

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName(Encryption.ENCRYPTWAY.stringValue());
        //散列的次数;
        hashedCredentialsMatcher.setHashIterations(Encryption.TIMES.numValue());
        return hashedCredentialsMatcher;
    }

    //将自己的验证方式加入容器
    @Bean
    public shiroRealm shiroRealm(){
        shiroRealm Realm= new shiroRealm();
        Realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return  Realm;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager
                = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }

    @Bean
    public permissionFilter getPermissionFilter(){
         return new permissionFilter();
    }

    @Bean
    public FilterRegistrationBean registration(permissionFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilter= new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        //未登录(改用前后端分离),加入自定义的过滤器
        Map<String, Filter> medicalFilter =  new HashMap<>();
       // shiroFilterFactoryBean.setLoginUrl("/system/notLogin");
        Map<String,String> filterMapLink = new LinkedHashMap<String, String>();
        //登录
        filterMapLink.put("/system/login", "anon");
        //登出
        filterMapLink.put("/system/logout", "anon");
        //对静态资源放行
        filterMapLink.put("/images/**","anon");
        filterMapLink.put("/assets/**","anon");
        filterMapLink.put("/css/**","anon");
        filterMapLink.put("/js/**","anon");
        filterMapLink.put("/json/**","anon");
        //退出
        filterMapLink.put("/logout", "logout");
        //对所有用户认证(改为自定义的过滤器)
       //filterMapLink.put("/**", "authc");

        //让filter仍然通过注入的方式让spring进行管理，同时又不会被spring默认注册
        registration(getPermissionFilter());

        medicalFilter.put("medicalAuth", getPermissionFilter());
        shiroFilter.setFilters(medicalFilter);
        filterMapLink.put("/**", "medicalAuth");

        //错误页面，认证不通过跳转(改用前后端分离)
       // shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilter.setFilterChainDefinitionMap(filterMapLink);
         return  shiroFilter;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
