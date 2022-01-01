package com.example.ddd.config.interceptor;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingFilterConfig {
    @Bean
    public FilterRegistrationBean<RequestResponseWrappingLogFilter> loggingFilter() {
        FilterRegistrationBean<RequestResponseWrappingLogFilter> registrationBean
                = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestResponseWrappingLogFilter());
        registrationBean.addUrlPatterns("/products/*");

        return registrationBean;
    }
}