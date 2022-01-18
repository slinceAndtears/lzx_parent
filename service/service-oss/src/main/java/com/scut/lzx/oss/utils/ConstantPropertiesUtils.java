package com.scut.lzx.oss.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class ConstantPropertiesUtils implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(ConstantPropertiesUtils.class);
    @Value("${aliyun.oss.file.endpoint}")
    private String endPoint;
    @Value("${aliyun.oss.file.keyid}")
    private String keyId;
    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;


    //初始化之后执行的方法
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("oss data initialization");
    }
}
