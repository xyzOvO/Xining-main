package com.xyz66.service;

import com.xyz66.domain.ResponseResult;
import com.xyz66.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();

}
