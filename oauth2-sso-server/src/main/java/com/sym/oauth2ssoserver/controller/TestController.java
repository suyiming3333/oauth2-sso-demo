package com.sym.oauth2ssoserver.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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


    @RequestMapping(value = "/getUserInfo")
    public Object getUserInfo(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return name;
    }

    @RequestMapping(value = "/session")
    public Map<String, Object> getSession(HttpServletRequest request) {
        request.getSession().setAttribute("username", "admin");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sessionId", request.getSession().getId());
        return map;
    }

    @RequestMapping(value = "/get")
    public String get(HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("username");

        return userName;
    }
}
