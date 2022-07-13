package com.dameng.common.core.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Description: 获取当前行号</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2021/6/4 14:06
 **/

@Slf4j
public class MethodUtil {

    /**
     * 私有化工具类 防止被实例化
     */
    private MethodUtil() {
    }


    public static String getLineInfo() {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        return ste.getFileName() + " -> " + ste.getLineNumber() + "行";
    }

}
