package com.dameng.common.core.excepetion;

import com.dameng.common.core.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>Description: 异常处理类</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2021/6/4 14:06
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * Description： 特定异常
     * @param e 1
     * @version 0.1.0
     * @return com.dameng.common.result.R
     * @author dameng
     * @date 2021/6/4 14:18
     * @since 0.1.0
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e) {
        log.error("特定异常");
        e.printStackTrace();
        return R.error().message(e.getMessage());
    }

    /**
     * Description： 自定义异常
     * @param e 1
     * @version 0.1.0
     * @return com.dameng.base.result.R
     * @author dameng
     * @date 2021/6/4 15:53
     * @since 0.1.0
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public R error(CustomException e) {
        log.error(e.getMsg());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }


    /**
     * Description：  权限异常
     * @param e 1
     * @version 0.1.0
     * @return com.dameng.base.result.R
     * @author dameng
     * @date 2021/6/4 15:53
     * @since 0.1.0
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseBody
    public R exceptionHandler(HttpServletRequest req, AccessDeniedException e){
        log.error("不允许访问！原因是:",e.getMessage());
        return R.error();
    }


    /**
     * Description：  指定出现什么异常执行这个方法
     * @param e 1
     * @version 0.1.0
     * @return com.dameng.common.result.R
     * @author dameng
     * @date 2021/6/4 14:18
     * @since 0.1.0
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        log.error("指定出现什么异常执行这个方法");
        e.printStackTrace();
        return R.error().message(e.getMessage());
    }

}
