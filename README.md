## SpringBoot

## 技术背景

spring提供了IOC完成了组件的注入和依赖管理. 但是在一些常用场景下,组件的配置往往是很雷同的.比如web开发,就是需要配置SpringMVC几个组件(中央控制器,前置处理器,视图解析器,渲染器等等), 然后数据层需要配置DataSource等, 这些基础组件性的配置和业务组件配置放在同一块代码,容易出现错误. 这些重复的配置需要被自动实现.

springboot就是来解决这个问题, 只要导入场景启动器(xxxStarter), 在application.yml中将组件不符合约定的配置项写一下,就可以完成基础组件的自动装配.  自动识别场景, 自动将Configuration配置类中的@Bean条件注入到容器中, 组件配置抽离出xxxProperties.class POJO, 完成和yml映射,即提供了default属性配置, 也支持覆盖.

拓展性, springboot这套自动配置流程也提供了拓展, 方便第三方框架融合到spring中, 第三方框架需要提供 1. starter场景启动器(聚合依赖) 2.编写自己的XXXAutoConfiguration.class .   用户方只要注入场景启动器依赖,在yml个性化覆盖配置就可以使用了.

https://blog.csdn.net/qq_21310939/article/details/107401400

## 自动配置原理

> 总结: 在pom.xml中添加场景启动器xxxx.starter(描述了我想要的场景), 然后@SpringBootApplication就会自动注入相关组件到容器, 需要更改掉组件的默认配置,就写到yml中

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

    

    