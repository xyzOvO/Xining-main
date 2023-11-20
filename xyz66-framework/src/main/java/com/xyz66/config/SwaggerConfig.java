package com.xyz66.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    public static final String TAG_1 = "文章";
    public static final String TAG_2 = "分类";
    public static final String TAG_3 = "登录";
    public static final String TAG_4 = "评论";
    public static final String TAG_5 = "用户";
    public static final String TAG_6 = "友链";

    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 扫描指定包中的swagger注解
                // .apis(RequestHandlerSelectors.basePackage("com.xyz66.project.tool.swagger"))
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Xyz66", "https://blog.csdn.net/yjj1123456", "2910223554@qq.com");
        return new ApiInfoBuilder()
                .title("西宁野生动物园论坛")
                .description("你好，世界！西宁野生动物园论坛的接口文档（Swagger）")
                .version("1.1.1")  // 版本
                .build();
    }
}
