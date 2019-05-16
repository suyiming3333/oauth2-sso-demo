package com.sym.oauth2ssoserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sym.oauth2ssoserver.mapper")
public class Oauth2SsoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2SsoServerApplication.class, args);
    }

}
