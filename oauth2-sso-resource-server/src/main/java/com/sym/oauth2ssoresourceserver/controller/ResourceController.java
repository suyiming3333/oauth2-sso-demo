package com.sym.oauth2ssoresourceserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: ResourceController
 * @Package com.sym.oauth2ssoresourceserver.controller
 * @Description: TODO
 * @date 2019/5/30 23:09
 */

@RestController
@RequestMapping("/resource")
public class ResourceController {


    @RequestMapping("/list")
    public List getResorceList(){
        List list = new ArrayList();
        list.add("resource a");
        list.add("resource b");
        return list;
    }
}
