package com.scut.lzx.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scut.lzx.commonutils.R;
import com.scut.lzx.entity.CrmBanner;
import com.scut.lzx.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表
 * </p>
 *
 * @author Zi-Xing li
 * @since 2022-04-22
 */
@RestController
@CrossOrigin
@RequestMapping("/educms/banneradmin")
public class BannerAdminController {
    private static final Logger logger = LoggerFactory.getLogger(BannerAdminController.class);

    @Autowired
    private CrmBannerService crmBannerService;

    @ApiOperation("分页查询banner")
    @GetMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable long page, @PathVariable long limit) {
        Page<CrmBanner> pageBanner = new Page<>(page, limit);
        crmBannerService.page(pageBanner, null);
        logger.info("get page banner {}", pageBanner);
        return R.ok().data("items", pageBanner.getRecords())
                .data("total", pageBanner.getTotal());
    }

    @ApiOperation("添加banner")
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner){
        boolean save = crmBannerService.save(crmBanner);
        logger.info("save banner {}", crmBanner);
        return R.ok();
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public R updateById(@RequestBody CrmBanner banner) {
        crmBannerService.updateById(banner);
        logger.info("update banner {}", banner);
        return R.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        crmBannerService.removeById(id);
        logger.info("remove  banner by id: {}", id);
        return R.ok();
    }
}

