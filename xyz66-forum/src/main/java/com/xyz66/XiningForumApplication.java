package com.xyz66;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * @author xyz66 Email:2910223554@qq.com
 */
@SpringBootApplication
@MapperScan("com.xyz66.mapper")
@EnableScheduling
@EnableSwagger2
public class XiningForumApplication {
    public static void main(String[] args) {
        SpringApplication.run(XiningForumApplication.class,args);
    }
}
