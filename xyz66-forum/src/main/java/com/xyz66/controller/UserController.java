package com.xyz66.controller;

import com.xyz66.annotation.SystemLog;
import com.xyz66.config.SwaggerConfig;
import com.xyz66.domain.ResponseResult;
import com.xyz66.domain.entity.User;
import com.xyz66.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = SwaggerConfig.TAG_5)
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 查看当前用户信息
     *
     * @return ResponseResult 当前用户信息
     */
    @ApiOperation(value = "查看当前用户信息")
    @GetMapping("/userInfo")
    public ResponseResult userInfo() {
        return userService.userInfo();
    }


    @SystemLog(businessName = "更新用户信息")
    @ApiOperation(value = "更新用户信息")
    @ApiImplicitParam(name = "user", value = "传入一个用户")
    @PutMapping("/userInfo")
    public ResponseResult updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    @ApiOperation(value = "用户注册") // 用户注册的API操作描述
    @ApiImplicitParam(name = "user", value = "传入一个用户注册信息") // 用户注册信息参数的描述
    @PostMapping("/register") // 注册用户的请求路径
    public ResponseResult register(@RequestBody User user) { // 注册用户的请求方法
        return userService.register(user); // 调用用户服务的注册方法，并返回注册结果
    }

}
