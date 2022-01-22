package com.example.book.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.book.entity.CoreEntity;
import com.example.book.entity.OfCoreUserEntity;
import com.example.book.mapper.CoreMapper;
import com.example.book.util.UtilJudge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoreService extends ServiceImpl<CoreMapper, CoreEntity> {
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;
    @Autowired
    private OfCoreUserService ofCoreUserService;

    /**
     * 查询用户和中心是否存在，如存在建立关联。
     *
     * @param coreName
     * @param userName
     * @return
     */
    public boolean changeUser(String coreName, String userName) {
        if (UtilJudge.isBlank(coreName) || UtilJudge.isBlank(userName)) {
            return false;
        }
        if (this.getCore(coreName) == null || userService.getUser(userName) == null) {
            return false;
        }
        OfCoreUserEntity ofCoreUser = new OfCoreUserEntity();
        ofCoreUser.setCoreId(this.getCore(coreName).getCoreId());
        ofCoreUser.setUserId(userService.getUser(userName).getId());
        ofCoreUserService.save(ofCoreUser);
        return true;
    }


    public CoreEntity getCore(String coreName) {
        QueryWrapper<CoreEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("core_name", coreName);
        return this.getOne(wrapper);
    }
}
