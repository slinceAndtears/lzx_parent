package com.scut.lzx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: lzx_parent
 * @description:
 * @author: Zi-Xing Li
 * @create: 2022-05-04 13:13
 **/

@SpringBootApplication
@MapperScan("com.scut.lzx.mapper")
@ComponentScan("com.scut")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
