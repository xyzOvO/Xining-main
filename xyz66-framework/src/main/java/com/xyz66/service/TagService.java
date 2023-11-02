package com.xyz66.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xyz66.domain.ResponseResult;
import com.xyz66.domain.dto.TagListDto;
import com.xyz66.domain.entity.Tag;
import com.xyz66.domain.vo.PageVo;
import com.xyz66.domain.vo.TagVo;

import java.util.List;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2022-07-19 22:33:38
 */
public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    List<TagVo> listAllTag();
}

