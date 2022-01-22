package com.example.book.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.book.entity.UserEntity;
import com.example.book.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService extends ServiceImpl<UserMapper, UserEntity> {
    /**
     * 判断用户权限
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回用户权限范围
     */
    public String userLevel(String username, String password) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        UserEntity user = this.getOne(wrapper);
        if (Objects.equals(user.getPassword(), password)) {
            if (user.getLevel() == 0) {
                return "0";
            } else if (user.getLevel() == 1) {
                return "1";
            }
            return "2";
        }
        return "error";
    }

    /**
     * 根据name查返回一个用户实体
     *
     * @param userName
     * @return
     */
    public UserEntity getUser(String userName) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userName);
        return this.getOne(wrapper);
    }
}





