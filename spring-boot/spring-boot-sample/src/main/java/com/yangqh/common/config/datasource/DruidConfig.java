package com.yangqh.common.config.datasource;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.google.common.collect.Maps;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Map;

/**
 * @author yangq
 * Create time in 2018-07-20 21:24
 */
@Configuration
public class DruidConfig {
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParameters = Maps.newHashMap();
        initParameters.put("loginUsername", "admin");//属性见：com.alibaba.druid.support.http.ResourceServlet
        initParameters.put("loginPassword", "123456");
        initParameters.put("allow", "");//默认允许所有
        initParameters.put("deny", "");
        bean.setInitParameters(initParameters);
        return bean;
    }

    /**
     * 配置一个web监控的filter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(new WebStatFilter());
        filterBean.setUrlPatterns(Arrays.asList("/*"));
        Map<String, String> initParameters = Maps.newHashMap();
        initParameters.put("exclusions", "*.js,*.css,/druid/*");//属性见：com.alibaba.druid.support.http.WebStatFilter
        filterBean.setInitParameters(initParameters);
        return filterBean;
    }
}
