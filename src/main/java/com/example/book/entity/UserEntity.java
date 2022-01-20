package com.example.book.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("user")
@Data
public class UserEntity implements Serializable {
    //    @TableId(type = IdType)
    private Long id;
    private String username;
    private String password;
    private Date time;
    private Date date;
    private int level;
}
