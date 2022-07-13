package com.dameng.common.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * <p>Description: Json工具类</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2021/6/4 14:06
 **/

public class JsonChange {
    /**
     * Description：  json转换成对象
     * @param obj 1
     * @param jsonStr 2
     * @version 0.1.0
     * @return java.lang.Object
     * @author dameng
     * @date 2021/5/28 8:27
     * @since 0.1.0
     */
    public static Object jsonToObj(Object obj,String jsonStr) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return obj = mapper.readValue(jsonStr, obj.getClass());
    }
    /**
     * Description：  对象转换成json
     * @param obj 1
     * @version 0.1.0
     * @return java.lang.String
     * @author dameng
     * @date 2021/5/28 8:27
     * @since 0.1.0
     */
    public static String objToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}
