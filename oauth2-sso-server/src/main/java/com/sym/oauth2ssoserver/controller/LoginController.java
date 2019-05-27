package com.sym.oauth2ssoserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: LoginController
 * @Package com.sym.oauth2ssoserver.controller
 * @Description: TODO
 * @date 2019/5/18 21:40
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

}