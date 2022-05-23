package com.scut.lzx.controller;

import com.scut.lzx.commonutils.R;
import com.scut.lzx.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: lzx_parent
 * @description: no
 * @author: Zi-Xing Li
 * @create: 2022-05-03 20:34
 **/

@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {
    @Autowired
    private MsmService msmService;

    @GetMapping("send/${phone}")
    public R senMsm(@PathVariable String phone){
        //TODO 根据手机号发送短信
        return R.ok();
    }
}
