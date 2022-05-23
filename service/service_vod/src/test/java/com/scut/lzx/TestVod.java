package com.scut.lzx;

import com.aliyun.oss.ClientException;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class TestVod {
    private static final String id = "804dd4165589404c8cef1d304cae87c5";
    private static final String accessKeyId = "LTAI4G7NCgA7NBHEc3Z8BZT1";
    private static final String accessKeySecret = "BNC9cyvEKPFpgfsuyjtSOggTvwM7hO";

    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    public static void getPlayUrl() throws com.aliyuncs.exceptions.ClientException {
        DefaultAcsClient client = initVodClient(accessKeyId, accessKeySecret);
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(id);
        GetPlayInfoResponse response = client.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        System.out.println(playInfoList.size());
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.println(playInfo.getPlayURL());
        }
        System.out.println(response.getVideoBase().getTitle());
    }

    public static void getAuth() throws com.aliyuncs.exceptions.ClientException {
        DefaultAcsClient client = initVodClient(accessKeyId, accessKeySecret);
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(id);
        GetVideoPlayAuthResponse acsResponse = client.getAcsResponse(request);
        System.out.println(acsResponse.getPlayAuth());
    }


    public static void uploadVideo() {
        String fileName = "src/main/resources/rookie-6.mp4";
        //File f=new File(fileName);
        //System.out.println(f.exists());
        String title = "rookie-6.mp4";
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        request.setPartSize(10 * 1024 * 1024L);
        request.setTaskNum(1);
        UploadVideoImpl uploadVideo = new UploadVideoImpl();
        UploadVideoResponse response = uploadVideo.uploadVideo(request);
        System.out.println(response.getRequestId());
        System.out.println(response.isSuccess());
        System.out.println(response.getMessage());
    }

    @Test
    public void test(){

    }
}