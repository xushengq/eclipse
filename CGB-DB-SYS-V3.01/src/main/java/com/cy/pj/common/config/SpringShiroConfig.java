package com.cy.pj.common.config;

import java.util.LinkedHashMap;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class SpringShiroConfig {
	/**spring 框架管理此对象时,会基于此对象管理Shiro框架中相关API对象的声明周期
	 
	 * 
	 * 
	 * @return
	 */
	
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
			 return new LifecycleBeanPostProcessor();
	}

    
	@Bean
	public SecurityManager securityManager(Realm realm,CacheManager CacheManager,
			CookieRememberMeManager rememberMeManager) {
		 DefaultWebSecurityManager sManager=
		 new DefaultWebSecurityManager();
		 sManager.setRealm(realm);
		 sManager.setCacheManager(CacheManager);
		 sManager.setRememberMeManager(rememberMeManager);
		 return sManager;
}
	@Bean
	 public CookieRememberMeManager rememberMeManager() {
		 CookieRememberMeManager cManager=
		 new CookieRememberMeManager();
       SimpleCookie cookie=new SimpleCookie("rememberMe");
		 cookie.setMaxAge(10*60);
		 cManager.setCookie(cookie);
		 return cManager;
	 }

	/**
	 * 配置此对象的目的是,在spring启动时,扫描所有的advisor对象,基于advisor对象中切入点的描述,为目标对象创建代理对象
	 * @return
	 */
	@DependsOn("lifecycleBeanPostProcessor")
	@Bean
	public DefaultAdvisorAutoProxyCreator newDefaultAdvisorAutoProxyCreator() {
			 return new DefaultAdvisorAutoProxyCreator();
	}


	@Bean
	public ShiroFilterFactoryBean shiroFilterFactory (
				 @Autowired SecurityManager securityManager) {
			 ShiroFilterFactoryBean sfBean=new ShiroFilterFactoryBean();
			 sfBean.setSecurityManager(securityManager);
			 sfBean.setLoginUrl("/doLoginUI");
			 //定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
			 LinkedHashMap<String,String> map=
					 new LinkedHashMap<>();
			 //静态资源允许匿名访问:"anon"
			 map.put("/bower_components/**","anon");
			 map.put("/build/**","anon");
			 map.put("/dist/**","anon");
			 map.put("/plugins/**","anon");
			 map.put("/user/doLogin","anon");
			 map.put("/doLogout/","logout");
			 //除了匿名访问的资源,其它都要认证("authc")后访问
			// map.put("/**","authc");
			map.put("/**","user");
			 sfBean.setFilterChainDefinitionMap(map);
			 return sfBean;
		 }
	 @Bean
	 public AuthorizationAttributeSourceAdvisor 
	 newAuthorizationAttributeSourceAdvisor(
	 	    		    @Autowired SecurityManager securityManager) {
	 		        AuthorizationAttributeSourceAdvisor advisor=
	 				new AuthorizationAttributeSourceAdvisor();
	 advisor.setSecurityManager(securityManager);
	 	return advisor;
	 }
	 @Bean
	 public CacheManager shiroCacheManager(){
	 	 return new MemoryConstrainedCacheManager();
	 }


}
