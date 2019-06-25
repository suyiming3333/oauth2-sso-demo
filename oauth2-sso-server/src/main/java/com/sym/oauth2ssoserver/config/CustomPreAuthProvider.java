package com.sym.oauth2ssoserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: asd
 * @Package com.sym.oauth2ssoserver.config
 * @Description: TODO
 * @date 2019/6/25 22:56
 */
@Component("preAuthProvider")
public class CustomPreAuthProvider extends PreAuthenticatedAuthenticationProvider {

    @Autowired
    private AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> userService;

    public CustomPreAuthProvider(){
        super();
    }

    @PostConstruct
    public void init(){
        super.setPreAuthenticatedUserDetailsService(userService);
    }
}