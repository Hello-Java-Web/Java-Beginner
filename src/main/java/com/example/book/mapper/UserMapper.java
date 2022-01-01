package com.example.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.book.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper extends BaseMapper<UserEntity> {

}
