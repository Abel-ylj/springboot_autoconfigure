package com.itheima.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ConfigurationProperties
 * 该注解完成了yml中配置 和 jvm中配置对象obj 的映射, 将配置读到内存对象中, bean可以读该对象populate自己的属性,完成配置
 * prefix 表示 配置项的前缀
 * 配置项类中的类变量名必须要与 前缀之后的配置项名称保持 松散绑定（相同）
 */
@ConfigurationProperties(prefix = "jdbc")
public class JdbcProperties {

    private String url;
    private String driverClassName;
    private String username;
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
