package com.dameng.common.core.result;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: 统一返回结果处理类</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2021/6/4 14:06
 **/

@Data
@ApiModel(value="统一返回R对象", description="")
public class R {

    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<String, Object>();

    private R() {}

    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultEnum.SUCCESS.getCode());
        r.setMessage(ResultEnum.SUCCESS.getMsg());
        return r;
    }

    public static R ok(ResultEnum resultEnum) {
        R r = new R();
        r.setSuccess(true);
        r.setCode(resultEnum.getCode());
        r.setMessage(resultEnum.getMsg());
        return r;
    }

    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(20001);
        r.setMessage("失败");
        return r;
    }

    public static R error(ResultEnum resultEnum) {
        R r = new R();
        r.setSuccess(false);
        r.setCode(resultEnum.getCode());
        r.setMessage(resultEnum.getMsg());
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
