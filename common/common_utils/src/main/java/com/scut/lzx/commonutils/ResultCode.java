package com.scut.lzx.commonutils;

public enum ResultCode {
    SUCCESS(20000), ERROR(20001);
    public Integer code;

    ResultCode(Integer code) {
        this.code = code;
    }

}
