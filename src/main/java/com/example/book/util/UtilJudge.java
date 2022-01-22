package com.example.book.util;

/**
 * 判空
 *
 * @author ZhangYao
 * @date 2022-01-01 17:41
 */
public class UtilJudge {
    /**
     * 判断是否为空
     *
     * @param obj 需要判断的值
     * @return ture为空，false为非空
     */
    public static boolean isBlank(String obj) {
        return obj == null || obj.equals("");
    }

}
