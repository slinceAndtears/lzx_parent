package com.scut.lzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: lzx_parent
 * @description: msm
 * @author: Zi-Xing Li
 * @create: 2022-05-03 20:29
 **/

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.scut")
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class, args);
    }
}
