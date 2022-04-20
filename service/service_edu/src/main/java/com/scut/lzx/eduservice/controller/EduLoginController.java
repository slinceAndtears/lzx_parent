package com.scut.lzx.eduservice.controller;

import com.scut.lzx.commonutils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {
    private static final Logger logger = LoggerFactory.getLogger(EduLoginController.class);

    @PostMapping("login")
    public R login() {
        logger.info("user login");
        return R.ok().data("token", "admin");
    }

    @GetMapping("info")
    public R info() {
        logger.info("get user info");
        return R.ok()
                .data("roles", "[admin]")
                .data("name", "admin")
                .data("avatar", "https://lizixing-edu.oss-cn-guangzhou.aliyuncs.com/2020/11/14/OIP-C.jpg");
    }
}
