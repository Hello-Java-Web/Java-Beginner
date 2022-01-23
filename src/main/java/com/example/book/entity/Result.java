package com.example.book.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private Long code;
    private String message;
    private Object obj;

    /**
     * 成功返回结果
     *
     * @param message 返回的消息
     * @return 成功返回结果
     */
    public static Result success(String message) {
        return new Result(1L, message, null);
    }

    /**
     * 成功返回结果与实体
     *
     * @param message
     * @param obj
     * @return
     */
    public static Result success(String message, Object obj) {
        return new Result(1L, message, obj);
    }

    /**
     * 返回错误信息
     *
     * @param message
     * @return
     */
    public static Result error(String message) {
        return new Result(0L, message, null);
    }

    /**
     * 返回错误信息与实体
     *
     * @param message
     * @param obj
     * @return
     */
    public static Result error(String message, Object obj) {
        return new Result(0L, message, obj);
    }

}
