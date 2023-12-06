package com.xyz66.service.impl;

import com.xyz66.domain.ResponseResult;
import com.xyz66.domain.entity.LoginUser;
import com.xyz66.domain.entity.User;
import com.xyz66.domain.vo.BlogUserLoginVo;
import com.xyz66.domain.vo.UserInfoVo;
import com.xyz66.service.BlogLoginService;
import com.xyz66.utils.BeanCopyUtils;
import com.xyz66.utils.JwtUtil;
import com.xyz66.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        // 获取用户输入的SpringSecurity的身份认证信息（用户、密码）
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        // 获取认证的Authentication对象
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过，没有该用户抛出异常
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid 生成token
        // Authentication对象中包含了用户信息,通过.getPrincipal()获取用户信息,强转成登录用户类
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        // 获取用户id
        String userId = loginUser.getUser().getId().toString();
        // 根据用户id生成token，jwt
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject("bloglogin:"+userId,loginUser);

        //把token和userinfo封装 返回
        //把User转换成UserInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogUserLoginVo vo = new BlogUserLoginVo(jwt,userInfoVo);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult logout() {
        //获取token 解析获取userid
        // 获取当前登录用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取userid
        Long userId = loginUser.getUser().getId();
        //删除redis中的用户信息
        redisCache.deleteObject("bloglogin:"+userId);
        return ResponseResult.okResult();
    }
}
