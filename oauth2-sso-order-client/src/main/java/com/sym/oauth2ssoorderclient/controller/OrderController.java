package com.sym.oauth2ssoorderclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: OderController
 * @Package com.sym.oauth2ssoorderclient
 * @Description: TODO
 * @date 2019/5/28 23:44
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/list")
    public String list() {
        return "order/list";
    }

}

