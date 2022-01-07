package com.example.book.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wechat")
public class WechatController {


    /**
     * 测试一下
     *
     * @return ok
     */
    @RequestMapping("/login")
    public String wechatLogin() {
        System.err.println("测试一下");
        return "ok";
    }


}
