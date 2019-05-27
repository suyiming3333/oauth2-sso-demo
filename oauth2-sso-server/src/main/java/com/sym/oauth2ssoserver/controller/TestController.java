package com.sym.oauth2ssoserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: TestController
 * @Package com.sym.oauth2ssoserver.controller
 * @Description: TODO
 * @date 2019/5/21 22:25
 */

@RestController
public class TestController {

    @RequestMapping(value = "/test")
    public String testHello(){
        return "hello!";
    }
}
