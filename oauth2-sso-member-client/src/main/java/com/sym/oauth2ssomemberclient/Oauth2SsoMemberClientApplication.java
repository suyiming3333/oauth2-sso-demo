package com.sym.oauth2ssomemberclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
/**加上这个才能在方法里进行拦截**/
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Oauth2SsoMemberClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2SsoMemberClientApplication.class, args);
    }

}
