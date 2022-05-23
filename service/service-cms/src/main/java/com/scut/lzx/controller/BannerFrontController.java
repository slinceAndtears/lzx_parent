package com.scut.lzx.controller;

import com.scut.lzx.commonutils.R;
import com.scut.lzx.entity.CrmBanner;
import com.scut.lzx.service.CrmBannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: lzx_parent
 * @description: Banner
 * @author: Zi-Xing Li
 * @create: 2022-04-22 21:14
 **/
@RestController
@CrossOrigin
@RequestMapping("/educms/bannerfront")
public class BannerFrontController {
    @Autowired
    private CrmBannerService bannerService;

    private static final Logger logger = LoggerFactory.getLogger(BannerFrontController.class);
    //查询所有banner
    @GetMapping("getAllBanner")
    public R getAllBanner() {
        List<CrmBanner> list = bannerService.selectAllBanner();
        logger.info("get banner list from front {}", list);
        return R.ok().data("list", list);
    }
}
