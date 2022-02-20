package com.example.dididemo.config;

import com.example.dididemo.filter.MyFilter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@Configurable
public class FilterConfig {
    /**
     * 配置跨域访问的过滤器
     * @return
    */    
    @Bean
    public FilterRegistrationBean registerFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.addUrlPatterns("/*");
        bean.setFilter(new MyFilter());
        return bean;
    }
}
