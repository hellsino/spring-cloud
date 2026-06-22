package com.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

// http://localhost:8080/swagger-ui.html
@EnableSwagger2 // 开启Swagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket1() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("dev");
    }

    @Bean // 配置docket以配置Swagger具体参数
    public Docket docket(Environment environment) {

        // 设置要显示swagger的环境
        Profiles of = Profiles.of("dev", "test");
        // 判断当前是否处于该环境
        // 通过 enable() 接收此参数判断是否要显示
        boolean b = environment.acceptsProfiles(of);

        return new Docket(
                DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("hellsino") // 配置分组
                .enable(true) // 配置是否启用Swagger，如果是false，在浏览器将无法访问
                .select()
                // 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口

                // any() 扫描所有，项目中的所有接口都会被扫描到
                // none() 不扫描接口
                // 通过方法上的注解扫描，如withMethodAnnotation(GetMapping.class)只扫描get请求
                // 通过类上的注解扫描，如.withClassAnnotation(Controller.class)只扫描有controller注解的类中的接口
                // basePackage(final String basePackage) // 根据包路径扫描接口
                .apis(RequestHandlerSelectors.basePackage("com.domain.controller"))

                // 配置如何通过path过滤,即这里只扫描请求以/www开头的接口
                // any() 任何请求都扫描
                // none() 任何请求都不扫描
                // regex(final String pathRegex) 通过正则表达式控制
                // ant(final String antPattern) 通过ant()控制

                //.paths(PathSelectors.ant("/www/**"))
                .build();
    }

    // 配置文档信息
    private ApiInfo apiInfo() {

        // 作者信息
        Contact DEFAULT_CONTACT = new Contact("", "", "");

        return new ApiInfo(
                "Api Documentation",
                "Api Documentation",
                "1.0", "urn:tos",
                DEFAULT_CONTACT, "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
