package com.dameng.common.core.excepetion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>Description: 自定义异常类</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2021/6/4 15:51
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomException extends RuntimeException{
    private Integer code;
    private String msg;
}
