package com.example.book.controller;

import com.example.book.entity.Result;
import com.example.book.entity.UserEntity;
import com.example.book.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

// 控制器注解，负责跳转页面的
//@Controller
// 单纯只返回数据
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    //http://localhost:8080/login?username=root&password=1231网页直接传参数

    /**
     * 主页登录
     * *@return
     */
    @RequestMapping("/UULogin")
    public String login() {
        return "/Login.html";
    }

    /**
     * 注册
     *
     * @param userEntity * @return
     */
    @RequestMapping("/logon")
    public Result logon(UserEntity userEntity) {
        // 形参，实参
        boolean ok = loginService.doLogon(userEntity);
        if (ok) {
            return Result.success("注册成功", userEntity);
        } else {
            return Result.error("用户名或密码错误");
        }
    }


    /**
     * 用户登录入口
     *
     * @param userEntity userEntity
     * @return 登录成功或者失败
     */
    @RequestMapping("/login") // 21点38分22秒，15秒，->sql
    public Result login(UserEntity userEntity, HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        boolean ok = loginService.doLogin(userEntity);
        if (ok) {
            return Result.success("登陆成功", userEntity);
        } else {
            return Result.error("用户名或密码错误");
        }
    }
}
