package com.example.book.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("book")
public class BookEntity implements Serializable {
    private Long id;
    private String name;
    private String writer;
    private Integer state;
    private Long userId;
    private Date foundTime;
    private Date userReturnTime;
    // 不遵守规范的写法，需要手动指定对应表的字段名称
    // @TableField("user_book")
    // private Long user_book;

}
