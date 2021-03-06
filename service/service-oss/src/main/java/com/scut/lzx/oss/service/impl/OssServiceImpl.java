package com.scut.lzx.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.scut.lzx.oss.service.OssService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    private static final Logger logger = LoggerFactory.getLogger(OssServiceImpl.class);

    @Value("${aliyun.oss.file.endpoint}")
    private String endPoint;
    @Value("${aliyun.oss.file.keyid}")
    private String keyId;
    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String url = "https://";
        OSS client = new OSSClientBuilder().build(endPoint, keyId, keySecret);
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") +
                file.getOriginalFilename();
        String dataPath = new DateTime().toString("yyyy/MM/dd") + "/" + fileName;
        try {
            InputStream stream = file.getInputStream();
            client.putObject(bucketName, dataPath, stream);
            url = url + bucketName + "." + endPoint + "/" + dataPath;
            logger.info("upload file {} to oss success", url);
        } catch (IOException e) {
            logger.error("get inputStream from file failure", e);
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
        return url;
    }
}
