package com.scut.lzx.controller;


import com.scut.lzx.commonutils.R;
import com.scut.lzx.entity.UcenterMember;
import com.scut.lzx.service.UcenterMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author Zi-Xing li
 * @since 2022-05-04
 */
@RestController
@CrossOrigin
@RequestMapping("/educenter")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @ApiOperation("登录接口")
    @PostMapping("login")
    public R login(@RequestBody UcenterMember ucenterMember){
        //封装手机号和密码
        String  token = ucenterMemberService.login(ucenterMember);
        return R.ok().data("token", token);
    }
}

