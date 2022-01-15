package com.scut.lzx.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {
    @ApiModelProperty(value = "表示请求是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data;

    private R() {
    }

    public static R ok() {
        R result = new R();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS.code);
        result.setMessage("成功");
        result.data = new HashMap<>();
        return result;
    }

    public static R error() {
        R result = new R();
        result.setCode(ResultCode.ERROR.code);
        result.setMessage("失败");
        result.data = new HashMap<>();
        result.setSuccess(false);
        return result;
    }

    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R data(String column, Object data) {
        this.getData().put(column, data);
        return this;
    }
}
