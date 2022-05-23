package com.scut.lzx.eduservice.client;


import com.scut.lzx.commonutils.R;
import com.scut.lzx.eduservice.client.impl.VodClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-vod", fallback = VodClientImpl.class)
public interface VodClient {

    @DeleteMapping("/eduvod/video/removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable("id") String id);
}
