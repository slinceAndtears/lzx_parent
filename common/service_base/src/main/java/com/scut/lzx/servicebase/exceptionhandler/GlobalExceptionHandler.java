package com.scut.lzx.servicebase.exceptionhandler;

import com.scut.lzx.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody//为了能够返回数据
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行了异常处理");
    }
}
