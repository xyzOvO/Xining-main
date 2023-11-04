package com.xyz66.controller;

import com.xyz66.annotation.SystemLog;
import com.xyz66.config.SwaggerConfig;
import com.xyz66.domain.ResponseResult;
import com.xyz66.domain.entity.User;
import com.xyz66.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apiguardian.api.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(tags = SwaggerConfig.TAG_5)
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/userInfo")
    @ApiOperation(value = "查看当前用户信息")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    @SystemLog(businessName = "更新用户信息")
    @ApiOperation(value = "更新用户信息")
    @ApiImplicitParam(name = "user" ,value = "传入一个用户")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    @ApiImplicitParam(name = "user" ,value = "传入一个用户注册信息")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }
}
