package com.leaforbook.orange.config;

import com.leaforbook.orange.util.HasResourceInterceptor;
import com.leaforbook.orange.util.SessionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfigure implements WebMvcConfigurer {

    @Autowired
    private SessionResolver sessionResolver;

    @Autowired
    private HasResourceInterceptor hasRoleInterceptor;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(sessionResolver);
        // equivalent to <mvc:argument-resolvers>
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(hasRoleInterceptor);
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // equivalent to <mvc:message-converters>
    }
}