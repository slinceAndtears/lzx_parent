package com.scut.lzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scut.lzx.entity.CrmBanner;
import com.scut.lzx.mapper.CrmBannerMapper;
import com.scut.lzx.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author Zi-Xing li
 * @since 2022-04-22
 */

@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    private static final Logger logger = LoggerFactory.getLogger(CrmBannerServiceImpl.class);

    @Cacheable(key = "'selectIndexList'", value = "banner")
    @Override
    public List<CrmBanner> selectAllBanner() {
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 2");
        List<CrmBanner> crmBanners = baseMapper.selectList(queryWrapper);
        logger.info("select banner list {}", crmBanners);
        return crmBanners;
    }
}
