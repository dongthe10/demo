package com.hollly.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

/**
 * @author hollly
 * @date 2022/8/20 20:30
 */
@Configuration
@EnableSwagger2 //开启Swagger2
//@EnableKnife4j
public class SwaggerConfig {

    @Resource
    private Environment environment;


    //配置 Swagger的Docket的Bean实例
    @Bean
    public Docket docket(){
        /*return new Docket(DocumentationType.SWAGGER_2)
                .enable(false)  // 是否启用swagger
                .apiInfo(apiInfo());*/
        // 获取profiles，来决定swagger是否开启
        String property = environment.getProperty("spring.profiles.active");

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //RequestHandlerSelectors，配置要扫描的接口方法
                //basePackage：指定要扫描的包
                //any()：扫描全部
                //none()：都不扫描
                //withClassAnnotation()：扫描类上的注解——参数是一个注解的反射对象
                //withMethodAnnotation()：扫描方法上的注解——get post
                .apis(RequestHandlerSelectors.basePackage("com.hollly.example.controller"))
                .paths(PathSelectors.ant("/**"))//过滤地址 PathSelectors.any()也可以使用
                .build();//工厂模式
    }
    //配置Swagger 信息apiInfo
    private ApiInfo apiInfo(){
        //作者信息
        Contact DEFAULT_CONTACT = new Contact("hollly", "http://www.baidu.com/", "xxxxxxxxxx@qq.com");


        // 使用ApiInfoBuilder更加优雅
        return new ApiInfoBuilder().title("swagger api文档-demo")
                .version("0.1")
                .description("i am description")
                .contact(DEFAULT_CONTACT)
                .termsOfServiceUrl("i am ternsOfServiceUrl")
                .license("i am license")
                .licenseUrl("http://license/url")
                .build();


        /*return new ApiInfo("SwaggerAPI文档",
                "description！",
                "1.0",
                "http://www.baidu.com/",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());*/
    }

}
