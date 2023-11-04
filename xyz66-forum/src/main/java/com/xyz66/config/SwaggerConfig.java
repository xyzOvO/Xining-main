package com.xyz66.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    public static final String TAG_1 = "文章";
    public static final String TAG_2 = "?";
    public static final String TAG_3 = "登录";
    public static final String TAG_4 = "评论";
    public static final String TAG_5 = "用户";

    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xyz66.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Xyz66", "https://blog.csdn.net/yjj1123456", "2910223554@qq.com");
        return new ApiInfoBuilder()
                .title("西宁野生动物园论坛")
                .description("你好，世界！西宁野生动物园论坛的接口文档（Swagger）")
                .contact("18325238013")   // 联系方式
                .version("1.1.1")  // 版本
                .build();
    }
}