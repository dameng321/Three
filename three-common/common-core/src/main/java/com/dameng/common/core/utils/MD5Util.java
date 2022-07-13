package com.dameng.common.core.utils;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * <p>Description: MD5工具类</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2021/6/4 14:06
 **/

public class MD5Util {

    /**
     * 获取字符串md5
     *
     * @param str
     * @return
     */
    public static String getStrMD5(String str) {
        String encodeStr = DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
        return encodeStr;
    }
    /**
     * 获取字符串md5值
     * @param str
     * @param times
     * @param salt
     * @return
     */
    public static String getMD5Code(String str, int times, String salt) {
        String s = str+salt;
        for (int i = 0; i < times; i++) {
            s = DigestUtils.md5DigestAsHex(s.getBytes(StandardCharsets.UTF_8));
        }
        return s;
    }

}