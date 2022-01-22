package com.example.book.controller;

import com.example.book.entity.Result;
import com.example.book.service.CoreService;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@RequestMapping("/core")
public class CoreController {
    @Resource
    private CoreService coreService;


    @RequestMapping("/changeUser")
    public Result coreChangeUser(String coreName, String userName) {
        boolean ok = coreService.changeUser(coreName, userName);
        if (ok) {
            return Result.success("中心添加用户成功");
        }
        return Result.error("中心添加用户失败");
    }

}
