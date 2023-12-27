package com.xyz66.controller;

import com.xyz66.config.SwaggerConfig;
import com.xyz66.domain.ResponseResult;
import com.xyz66.domain.entity.User;
import com.xyz66.enums.AppHttpCodeEnum;
import com.xyz66.exception.SystemException;
import com.xyz66.service.BlogLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 * @author xyz66 Email:2910223554@qq.com
 */
@Api(tags = SwaggerConfig.TAG_3)
@RestController
public class ForumLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    @ApiOperation(value = "登录功能")
    @ApiImplicitParam(name = "user",value = "传入用户")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "退出登录")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }
}
