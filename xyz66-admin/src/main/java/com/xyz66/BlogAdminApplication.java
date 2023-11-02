package com.xyz66;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xyz66 Email:2910223554@qq.com
 */
@SpringBootApplication
@MapperScan("com.xyz66.mapper")
public class BlogAdminApplication {
    public static void main(String[] args) {

        SpringApplication.run(BlogAdminApplication.class, args);
    }
}

