package com.example.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.book.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author ZhangYao
 * @date 2022-01-01 17:13
 */
@Mapper
@Component
public interface RoleMapper extends BaseMapper<RoleEntity> {


}
