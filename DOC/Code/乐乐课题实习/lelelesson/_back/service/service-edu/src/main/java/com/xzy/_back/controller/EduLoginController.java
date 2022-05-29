package com.xzy._back.controller;

import com.xzy._back.commonutils2.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.ResultSet;

@Api(description = "用户登录管理")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {
    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    public RestResult login(){
        return RestResult.ok().data("token", "admin");
    }
    @ApiOperation(value = "获取用户信息")
    @GetMapping("info")
    public RestResult info(){
        return RestResult.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://coderwithhair.com.cn/images/cat.jpg");
    }
}