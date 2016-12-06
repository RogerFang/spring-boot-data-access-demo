package com.roger.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * druid配置方式二
 * Created by Roger on 2016/12/6.
 */
@Configuration
public class DruidConfig {

    /**
     * 注册一个StatViewServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean DruidStatViewServlet2(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid2/*");

        // 添加初始化参数: initParams

        // IP白名单
        // servletRegistrationBean.addInitParameter("allow", "");
        // IP黑名单
        // servletRegistrationBean.addInitParameter("deny", "");
        // 登录查看信息的账号密码
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        // 是否能够重置数据
        // servletRegistrationBean.addInitParameter("resetEnabled", "false");
        return servletRegistrationBean;
    }

    /**
     * 注册一个StatFilter
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter2(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.css,*.jpg");
        return filterRegistrationBean;
    }
}
