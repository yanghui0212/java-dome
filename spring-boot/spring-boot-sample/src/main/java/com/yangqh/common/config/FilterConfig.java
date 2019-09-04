package com.yangqh.common.config;

import com.yangqh.common.config.filter.CsrfFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author yangq
 * Create time in 2018-07-13 14:58
 */
@Configuration
@Order(5)
public class FilterConfig {

    @Bean
    public FilterRegistrationBean csrfFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new CsrfFilter());
        return filterRegistrationBean;
    }
}
