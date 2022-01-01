package com.example.book.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhangYao
 * @date 2022-01-01 17:13
 */
@Data
public class RoleEntity implements Serializable {



    private Long id;

    private String name;

    private Boolean enable;

}
