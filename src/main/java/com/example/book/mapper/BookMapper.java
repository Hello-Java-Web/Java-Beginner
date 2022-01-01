package com.example.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.book.entity.BookEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BookMapper extends BaseMapper<BookEntity> {
}
