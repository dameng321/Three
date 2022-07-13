/**
 * FileName: RandomUtils
 * Author:   lvyabin
 * Date:     2019/2/6 10:11 PM
 * Description: 随机数工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.dameng.common.core.utils;

import java.util.Random;
import java.util.UUID;

/**
 * <p>Description: 随机数工具类</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2021/6/4 14:06
 **/

public class RandomUtils {

    /**
     * 获取随机数
     *
     * @param start 开始的值
     * @param end   结束的值
     * @return
     */
    public static int getRandom(int start, int end) {
        Random random = new Random();
        int result = random.nextInt(end - start) + start;
        return result;
    }

    /**
     * 获取随机数字内容的字符串
     *
     * @param start  开始位置
     * @param end    结束位置
     * @param length 共几位
     * @return
     */
    public static String getRandom(int start, int end, int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(end - start) + start);
        }
        return sb.toString();
    }

    /**
     * 产生随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomStr(int length) {
        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            //产生0-61的数字
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String getUUID() {
        //转化为String对象
        String uuid = UUID.randomUUID().toString();
        //因为UUID本身为32位只是生成时多了“-”，所以将它们去点就可
        uuid = uuid.replace("-", "");
        return uuid;
    }

}