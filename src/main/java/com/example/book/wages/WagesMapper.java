package com.example.book.wages;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface WagesMapper extends BaseMapper<WagesUserEntity> {
}
