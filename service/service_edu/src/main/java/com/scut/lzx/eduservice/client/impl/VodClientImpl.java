package com.scut.lzx.eduservice.client.impl;

import com.scut.lzx.commonutils.R;
import com.scut.lzx.eduservice.client.VodClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @program: lzx_parent
 * @description: hystrix implement class
 * @author: Zi-Xing Li
 * @create: 2022-04-22 10:43
 **/
@Component
public class VodClientImpl implements VodClient {

    private static final Logger logger = LoggerFactory.getLogger(VodClientImpl.class);

    /**
     * @param id the videoSourceId
     * @return
     * @author Zi-Xing Li
     * @date
     */

    @Override
    public R removeAlyVideo(String id) {
        logger.error("RPC failure the videoSource id is {}", id);
        return R.error().message("删除视频出错, 视频id为" + id);
    }
}
