package com.xyz66;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.xyz66.mapper")
@EnableScheduling
@EnableSwagger2
public class Xyz66ForumApplication {
    public static void main(String[] args) {
        SpringApplication.run(Xyz66ForumApplication.class,args);
    }
}
