package com.scut.lzx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: lzx_parent
 * @description: springboot
 * @author: Zi-Xing Li
 * @create: 2022-04-22 21:06
 **/
@SpringBootApplication
@ComponentScan({"com.scut"})
@MapperScan("com.scut.lzx.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
