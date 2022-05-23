package com.scut.lzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scut.lzx.commonutils.JwtUtils;
import com.scut.lzx.commonutils.MD5;
import com.scut.lzx.entity.UcenterMember;
import com.scut.lzx.mapper.UcenterMemberMapper;
import com.scut.lzx.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scut.lzx.servicebase.exceptionhandler.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author Zi-Xing li
 * @since 2022-05-04
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember>
        implements UcenterMemberService {

    private static final Logger logger = LoggerFactory.getLogger(UcenterMemberServiceImpl.class);

    @Override
    public String login(UcenterMember ucenterMember) {
        String mobile = ucenterMember.getMobile();
        String password = ucenterMember.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            logger.error("用户手机号或者密码为空");
            throw new MyException(20001, "用户手机号或者密码为空");
        }

        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        UcenterMember mobileMember = baseMapper.selectOne(queryWrapper);
        password = MD5.encrypt(password);
        if (mobileMember == null || !mobileMember.getPassword().equals(password)) {
            logger.error("用户不存在或者密码错误， 手机号为 {}", mobile);
            throw new MyException(20001, "用户不存在或者密码为空");
        }

        if (mobileMember.getIsDisabled()) {
            logger.error("用户被禁用 {}", mobileMember);
            throw new MyException(20001, "登录的用户被禁用");
        }
        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
        return jwtToken;
    }
}
