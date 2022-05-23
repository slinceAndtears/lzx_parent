package com.scut.lzx.eduvod.utils;

import com.aliyun.oss.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @program: lzx_parent
 * @description: store the aliyun AccessKey id  and secret
 * @author: Zi-Xing Li
 * @create: 2022-04-21 14:23
 **/
public class AccessKey {
    public static final String ACCESS_KEY_ID = "LTAI4G7NCgA7NBHEc3Z8BZT1";
    public static final String ACCESS_KEY_SECRET = "BNC9cyvEKPFpgfsuyjtSOggTvwM7hO";
    public static final String REGION_ID = "cn-shanghai";

    public static DefaultAcsClient initVodClient() throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
