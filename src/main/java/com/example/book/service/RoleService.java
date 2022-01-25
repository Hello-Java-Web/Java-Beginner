package com.example.book.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.book.entity.RoleEntity;
import com.example.book.entity.RoleUserEntity;
import com.example.book.mapper.RoleMapper;
import com.example.book.mapper.RoleUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author ZhangYao
 * @date 2022-01-01 17:13
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, RoleEntity> {

    @Autowired
    private RoleUserMapper roleUserMapper;

    /**
     * 根据用户id返回是否包含某个角色
     *
     * @param userId
     * @param roleId
     * @return boolean
     * @author ZhangYao
     * @version V1.0.0
     * @since 2022/1/1 17:17
     */
    public boolean hasRole(Long userId, Long roleId) {
        // 根据条件去查询用户角色配置关系
        // 最终查询回来的列表，代表当前用户配置的所有角色
        List<RoleUserEntity> userEntityList = roleUserMapper.selectList(
                new QueryWrapper<>(
                        new RoleUserEntity().setUserId(userId)));
        // 查找当前用户是否配置了某个角色
        for (RoleUserEntity roleUserEntity : userEntityList) {
            // 找到了
            if (Objects.equals(roleUserEntity.getRoleId(), roleId)) {
                return true;
            }
        }
        // 没找到
        return false;
    }


}
