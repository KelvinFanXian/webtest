package com.kelvin.webtest.config;

import com.google.common.base.Predicate;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author Kelvin范显
 * @createDate 2018年01月26日
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("demo")
                .genericModelSubstitutes(DeferredResult.class)
//              .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .paths(or(regex("/demo/.*")))//过滤的接口
                .build()
                .apiInfo(demoApiInfo());
    }

    private ApiInfo demoApiInfo() {
        return new ApiInfoBuilder()
                .title("Electronic Health Record(EHR) Platform API")//大标题
                .description("EHR Platform's REST API, all the applications could access the Object model data via JSON.")//详细描述
                .version("1.0")//版本
                .termsOfServiceUrl("NO terms of service")
                .contact(new Contact("name", "url", "email"))//作者
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }

    @Bean
    public Docket createRestApi() {
        Predicate<RequestHandler> predicate = input -> {
            Class<?> declaringClass = input.declaringClass();
            if (declaringClass == BasicErrorController.class)// 排除
                return false;
            if(declaringClass.isAnnotationPresent(RestController.class)) // 被注解的类
                return true;
            if(input.isAnnotatedWith(ResponseBody.class)) // 被注解的方法
                return true;
            return false;
        };
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(predicate)
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .description("description")
                .termsOfServiceUrl("termsOfServiceUrl")
                .license("license")
                .title("Demo测试例子接口")//大标题
                .version("1.0")//版本
                .build();
    }
}
