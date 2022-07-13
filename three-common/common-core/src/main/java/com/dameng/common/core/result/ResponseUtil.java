package com.dameng.common.core.result;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Description: Response处理类</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2021/6/4 14:06
 **/

@ApiModel(value="Response处理类", description="")
public class ResponseUtil {

    public static void out(HttpServletResponse response, R r) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            mapper.writeValue(response.getWriter(), r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
