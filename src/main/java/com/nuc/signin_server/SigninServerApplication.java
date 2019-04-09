package com.nuc.signin_server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.nuc.signin_server.mapper")
public class SigninServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SigninServerApplication.class, args);
    }

}
