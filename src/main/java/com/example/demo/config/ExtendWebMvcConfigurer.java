package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * 用于扩展Web MVC的配置，如添加资源处理程序、视图解析器、拦截器， 参数转换器等
 */
@Configuration
public class ExtendWebMvcConfigurer implements WebMvcConfigurer {
    /**
     * 国际化解析器
     *
     */
    @Bean("localeResolver")
    public LocaleResolver initLocaleResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(java.util.Locale.CHINA);
        return localeResolver;
    }

    /**
     * 创建国际化拦截器
     *
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }
    @Override
    public void addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        registry.addInterceptor(new com.example.demo.interceptors.LogInterceptor()).addPathPatterns("/**");
        // 添加国际化拦截器
        registry.addInterceptor(localeChangeInterceptor());
    }
}
