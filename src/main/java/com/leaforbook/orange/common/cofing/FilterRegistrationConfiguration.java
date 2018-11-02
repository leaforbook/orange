package com.leaforbook.orange.common.cofing;

import com.leaforbook.orange.common.auth.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterRegistrationConfiguration {
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(loginFilter());
        registration.addUrlPatterns("/orange/*");
        registration.setName("loginFilter");
        registration.setOrder(1);
        return registration;
    }


    @Bean
    public Filter loginFilter() {
        return new LoginFilter();
    }
}
