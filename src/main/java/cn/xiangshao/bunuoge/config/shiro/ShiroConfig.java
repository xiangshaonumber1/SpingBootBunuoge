package cn.xiangshao.bunuoge.config.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 在ShiroConfig中将所有的请求指向JWT
 * 1.   指定自定义的MyRealm用于传入DefaultWebSecurityManager
 * 2.   在SecurityManager中关闭默认的 Session控制
 *      ——因为在前后端分离项目中前端是无法获取到后端Session的，即无法实现用户登录状态的同步
 * 3.   在shrioFilterFactoryBean()中传入自定义的 TokenFilter
 *      ——并将所有的请求指向该过滤器 filterRuleMap.put("/**","jwt")
 *      https://yq.aliyun.com/articles/646440
 */
@Slf4j
@Configuration
public class ShiroConfig {

    /**
     * 设置过滤器，将自定义的Filter加入
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){

        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        //添加自己的过滤器，并取名
        Map<String,Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("jwt",new JWTFilter());

        factoryBean.setFilters(filterMap);
        factoryBean.setSecurityManager(securityManager);

        //设置过滤名单
        factoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition().getFilterChainMap());

        return factoryBean;
    }


    //自定义shiroRealm
    @Bean
    public UserShiroRealm myShiroRealm(){
        return new UserShiroRealm();
    }


    @Bean("securityManager")
    public SecurityManager securityManager(UserShiroRealm userShiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 使用自己的 realm
        securityManager.setRealm(myShiroRealm());

        //关自带的session
        DefaultSessionStorageEvaluator evaluator = new DefaultSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(false);

        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        subjectDAO.setSessionStorageEvaluator(evaluator);

        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }

    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持
     * 注：一定要写入上面 advisorAutoProxyCreator() 自动代理。不然aop注解不会生效
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * shiro生命周期处理器
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }



    /**
     * 过滤名单详情
     */
    @Bean
    protected ShiroFilterChainDefinition shiroFilterChainDefinition(){
        //默认是LinkedList，按添加的顺序的来的
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        //注：配置权限是从上到下，上面的可以覆盖下面的，所以默认设置最好设置在最下面，需要单独配置的写在上面

        /* *************************************** Article 接口 权限设置 start update_article ********************************************************** **/
//        chainDefinition.addPathDefinition("/article/write_article", "noSessionCreation,jwt");
//        chainDefinition.addPathDefinition("/article/write_diary", "noSessionCreation,jwt");
//        chainDefinition.addPathDefinition("/article/get_myArticle", "noSessionCreation,jwt");
//        chainDefinition.addPathDefinition("/article/get_myDiary", "noSessionCreation,jwt");
//        chainDefinition.addPathDefinition("/article/update_article", "noSessionCreation,jwt");
//        chainDefinition.addPathDefinition("/article/update_diary", "noSessionCreation,jwt");
//        chainDefinition.addPathDefinition("/article/**", "noSessionCreation,anon");
        /* *************************************** Article 开放 权限设置 end ********************************************************** **/

        /* ************************************* Authentication 权限设置 start ***************************************************************** **/
//        chainDefinition.addPathDefinition("/Authentication/**", "noSessionCreation,anon");
        /* ************************************* Authentication 权限设置 end *********************************************************** **/

        /* ******************************************* User 权限相关设置 start **************************************************** */
//        chainDefinition.addPathDefinition("/user/updateUserInfo","noSessionCreation,jwt");
//        chainDefinition.addPathDefinition("/user/updateUserIcon","noSessionCreation,jwt");
//        chainDefinition.addPathDefinition("/user/update_pw","noSessionCreation,jwt");
//        chainDefinition.addPathDefinition("/user/**","noSessionCreation,anon");
        /* ******************************************  User 权限相关设置 end *****************************************/

//        chainDefinition.addPathDefinition("/graph/**","noSessionCreation,anon");
//        chainDefinition.addPathDefinition("/actuator/**","noSessionCreation,anon");
        //服务器访问资源
//        chainDefinition.addPathDefinition("/image/**", "noSessionCreation,anon");
        //允许访问静态资源（SpringBoot中static本就是开放，所以如果要访问静态目录下的资源，是必须要放开static下的第一级目,但static还是要
        // 放行，避免在同一台服务器上的基于nginx的项目访问静态资源时被拦截）
//        chainDefinition.addPathDefinition("/static/**","noSessionCreation,anon");
        //开放common公共资源接口
//        chainDefinition.addPathDefinition("/common/**","noSessionCreation,anon");

        //管理员专属权限，需要角色权限 , 只允许拥有 admin 或 root 角色的用户访问
//        chainDefinition.addPathDefinition("/admin/**","noSessionCreation,jwt");
        //服务图片查看权限
//        chainDefinition.addPathDefinition("/picture/**","noSessionCreation,anon");
//        chainDefinition.addPathDefinition("/avatar/**","noSessionCreation,anon");

        /* ********************** 开放swagger 文档接口， 一定要这样写，千万别改 *************************** */
//        chainDefinition.addPathDefinition("/swagger-ui.html", "anon");
//        chainDefinition.addPathDefinition("/webjars/**", "anon");
//        chainDefinition.addPathDefinition("/v2/**", "anon");
//        chainDefinition.addPathDefinition("/swagger-resources/**", "anon");
        /* ********************** 开放swagger 文档接口， 一定要这样写，千万别改 *************************** */
//        chainDefinition.addPathDefinition("/socket.io","anon");
//        chainDefinition.addPathDefinition("/socket.io/**","anon");

        //其余接口，一律拦截，配置默认需要验证，必须放在最后
//        chainDefinition.addPathDefinition("/**","noSessionCreation,jwt");

        return chainDefinition;
    }



}
