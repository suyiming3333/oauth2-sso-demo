package com.sym.oauth2ssoserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@MapperScan("com.sym.oauth2ssoserver.mapper")
@EnableRedisHttpSession
public class Oauth2SsoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2SsoServerApplication.class, args);
    }

}
