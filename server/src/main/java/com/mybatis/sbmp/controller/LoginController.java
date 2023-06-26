package com.mybatis.sbmp.controller;


import cn.dev33.satoken.stp.SaTokenInfo;
import com.mybatis.sbmp.bo.Ms;
import com.mybatis.sbmp.entity.Users;
import com.mybatis.sbmp.service.UserService;
import com.mybatis.sbmp.utils.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author zss
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserService userService;

    /**
     * 工程师登录
     * @return
     */
    @PostMapping("/ms")
    public AjaxResult<?> msLogin(@RequestBody Ms user) {
        SaTokenInfo token = userService.loginMs(user);
        return AjaxResult.success(token);
    }



}