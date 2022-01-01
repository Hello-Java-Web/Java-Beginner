package com.example.book.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.book.entity.UserEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Objects;

@Service
public class LoginService {

    // @Autowired
    @Resource
    private UserService userService;


    /**
     * 执行注册程序
     *
     * @param userEntity *@return lz
     */
    public boolean doLogon(UserEntity userEntity) {
        if (userEntity.getUsername() != null && !Objects.equals(userEntity.getUsername(), "") && userEntity.getPassword() != null && !Objects.equals(userEntity.getPassword(), "")) {
            QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("username", userEntity.getUsername());
            UserEntity storeUser = userService.getOne(wrapper);
            //判断账户不存在，存在返回false，
            if (storeUser == null) {
                userEntity.setTime(Calendar.getInstance().getTime());
                userEntity.setLevel(1);
                userService.save(userEntity);
                return true;
            }
            //执行注册，保存账号密码，并返回true

            // 为新注册的用户，新增普通用户角色
        }
        return false;
    }

    /**
     * 执行登录程序
     *
     * @param userEntity *@return lz
     */
    public boolean doLogin(UserEntity userEntity) {
        if (userEntity.getUsername() == null || Objects.equals(userEntity.getUsername(), "") || userEntity.getPassword() == null || Objects.equals(userEntity.getPassword(), "")) {
            return false;
        } else {
            // 查询包装器，负责封装对目标表的所有查询条件
            QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
            // String sql = "select * from user where username = root";数据库语句
            //查询包装器是一个UserEntity实例，把userEntity参数里的username值付给wrapper
            wrapper.eq("username", userEntity.getUsername()); // 拼装 username = #{user.username}
            //新建一个实例。保存wrapper通过username查询到的一行数据库值
            UserEntity user = userService.getOne(wrapper);
            if (user == null) {
                return false;
            }
            //判断账号和密码是否匹配
            return user.getPassword().equals(userEntity.getPassword());
        }
    }
}
