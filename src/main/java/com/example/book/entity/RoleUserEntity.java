package com.example.book.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * @author ZhangYao
 * @date 2022-01-01 17:13
 */
@Data
@Accessors(chain = true)
public class RoleUserEntity implements Serializable {


    private Long id;
    private Long roleId;
    private Long userId;




}
