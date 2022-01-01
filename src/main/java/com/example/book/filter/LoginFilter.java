package com.example.book.filter;

import com.example.book.controller.BookController;
import com.example.book.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 用户登录检查拦截器
 *
 * @author ZhangYao
 * @date 2022-01-01 17:36
 */
@Service
public class LoginFilter implements Filter {

    @Autowired
    private RoleService roleService;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入拦截器了");
        // 一定，固定写法。。。。
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

            // 检查url是否需要权限校验
            String requestURI = httpServletRequest.getRequestURI();

            AntPathMatcher matcher = new AntPathMatcher();
            boolean ok = matcher.match(requestURI, "/public/{id}?userid=123&password=5555");

            // 此处可以写死，在项目开始的时候属于协定部分
            String userId = httpServletRequest.getHeader("userId");
            if (roleService.hasRole(Long.valueOf(userId), BookController.注册用户)) {

                // 可以把用户id放入全局变量里之类的。。。

            } else {
                throw new RuntimeException("当前不是注册用户");
            }
        }
        // 必须写，进行http的后续业务
        filterChain.doFilter(servletRequest, servletResponse);
    }


}
