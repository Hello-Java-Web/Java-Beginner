package com.example.book.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.book.entity.MenuEntity;
import com.example.book.mapper.MenuMapper;
import org.springframework.stereotype.Component;

@Component
public class MenuService extends ServiceImpl<MenuMapper, MenuEntity> {


    public MenuEntity getMenu(String menuName) {
        QueryWrapper<MenuEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("menu_name", menuName);
        return this.getOne(wrapper);

    }
}
