package com.xyz66.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xyz66.domain.entity.RoleMenu;

/**
 * @Author 三更  B站： https://space.bilibili.com/663528522
 */
public interface RoleMenuService extends IService<RoleMenu> {

    void deleteRoleMenuByRoleId(Long id);
}