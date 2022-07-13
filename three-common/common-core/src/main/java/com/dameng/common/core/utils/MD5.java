package com.dameng.common.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Description: MD5加密类</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2021/6/4 14:06
 **/

public final class MD5 {

    public static String encrypt(String strSrc) {
        try {
            char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l',
                                'z','x','c','v','b','n','m'};
            byte[] bytes = strSrc.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            bytes = md.digest();
            int j = bytes.length;
            char[] chars = new char[j * 2];
            int k = 0;
            for (byte b : bytes) {
                chars[k++] = hexChars[b >>> 4 & 0xf];
                chars[k++] = hexChars[b & 0xf];
            }
            return new String(chars);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("MD5加密出错！！+" + e);
        }
    }

    public static void main(String[] args) {
        System.out.println(MD5.encrypt("111111"));
    }

}
