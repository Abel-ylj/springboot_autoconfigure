## 自动配置原理

1. springboot自动启动原理--在启动类上加了SpringBootApplication注解后就自动开启了 **自动配置EnableAutoConfiguration**

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration    -- 将扫描包下的@Configuration注解类,替代<bean>标签 --xml中的<bean对象
@EnableAutoConfiguration    -- 检查环境中的依赖starter,注入一堆相关的组件,可以启动一个功能 -- 组件bean
@ComponentScan(excludeFilters = {  -- 扫@Compenent @Service 等自定义bean -- 项目自定义bean
		@Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM,
				classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {
```



2. pom文件中依赖需要打开的starter

    ```java
    比如增加了spring-boot-starter-web依赖,则表示我要开启web功能
    
    spring需要识别出来自动给我配置好,spring自动会注入tomcat, mvc 相关的bean来开启web功能, 并设置好相关配置参数
    ```

    

3. **EnableAutoConfiguration原理**, springboot是如何通过spring-boot-starter-web这个依赖,就自动配置相关bean呢

    ```java
    大致逻辑
    1. @SpringBootApplication --> @EnableAutoConfiguration --> 该注解的包下有META-INF/spring.factories
      其中自动配置相关配置如下: 
      # Auto Configure
        org.springframework.boot.autoconfigure.EnableAutoConfiguration=\ //key,value
        org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
        org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\
        org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,\
        
    2. bootloader从classpath中查找META-INF/spring.factories 文件, <EnableAutoConfiguration, List<自动配置类>>
        
    2.1.  @AutoConfigurationPackage
        @Import(AutoConfigurationImportSelector.class) // 该类会去执行筛选逻辑
        public @interface EnableAutoConfiguration {
        }
    
    3. xxxAutoConfiguration类上有@condition即其衍生注解,描述该类依赖,依赖存在才会将Bean注册到容器,由2.1步骤逻辑执行
      @EnableConfigurationProperties(ServerProperties.class) -- // yml配置映射到对象obj
      @Import({otherConfiguration.staticClass.class, xxx}) -- 同xml中<import> 配置文件拆分的作用
      public class ServletWebServiceFactoryAutoConfiguration {
        @Bean
        public ServletWebServerFactoryCustomizer servletWebServerFactoryCustomizer(
          ServerProperties serverProperties) {  -- // 配置类会自动注入
          return new ServletWebServerFactoryCustomizer(serverProperties);
        }
      }
    
    @ConfigurationProperties(prefix = "server", ignoreUnknownFields = true)
    public class ServerProperties {
      private Integer port; 
      public static class Tomcat {
        private String protocolHeaderHttpsValue = "https";
    		private String portHeader = "X-Forwarded-Port";
      }
    }
    
    application.yml
      server
      	port: 可以覆盖默认的
    ```

    

    