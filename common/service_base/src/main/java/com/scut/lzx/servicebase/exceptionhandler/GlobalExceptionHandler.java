package com.scut.lzx.servicebase.exceptionhandler;

import com.scut.lzx.commonutils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody//为了能够返回数据
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行了异常处理");
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public R error(MyException e) {
        e.printStackTrace();
        logger.error("执行自定义异常", e);
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
