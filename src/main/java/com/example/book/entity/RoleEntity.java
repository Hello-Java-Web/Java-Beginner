package com.example.book.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhangYao
 * @date 2022-01-01 17:13
 */
@Data
@TableName("role")
public class RoleEntity implements Serializable {


    private Long id;

    private String name;

    private Boolean enable;

}
