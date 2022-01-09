package com.itheima.config;

import com.itheima.interceptor.MyInterceptor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties(JdbcProperties.class) // 该注解可以让此Conf中的bean读配置, 就需要使用spring-boot-configuration-processor
public class MvcConfig implements WebMvcConfigurer {

    //注册拦截器
    @Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }

    //添加拦截器到spring mvc拦截器链
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor()).addPathPatterns("/*");
    }

    @Bean("myObj")
    public MyObj testProperty(JdbcProperties jdbcProperties) {
        return MyObj.builder().url(jdbcProperties.getUrl())
                .driverClassName(jdbcProperties.getDriverClassName())
                .username(jdbcProperties.getUsername())
                .password(jdbcProperties.getPassword())
                .build();
    }

    @Data
    @Builder
    @ToString
    public static class MyObj {
        private String url;
        private String driverClassName;
        private String username;
        private String password;
    }
}
