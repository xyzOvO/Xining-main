package com.xyz66.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xyz66.domain.ResponseResult;
import com.xyz66.domain.entity.Link;
import com.xyz66.domain.vo.PageVo;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-02-03 12:22:53
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();

    PageVo selectLinkPage(Link link, Integer pageNum, Integer pageSize);
}

