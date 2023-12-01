package com.xyz66.controller;


import com.xyz66.domain.ResponseResult;
import com.xyz66.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author xyz66 Email:2910223554@qq.com
 */
@Api(tags = "上传")
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    /**
     * 上传图片
     *
     * @param img 上传的图片文件
     * @return 返回上传结果
     * @throws IOException 如果发生IO异常
     */
    @ApiOperation(value = "上传图片文件")
    @ApiImplicitParam(name = "img", value = "图片文件")
    @PostMapping("/upload")
    public ResponseResult uploadImg(MultipartFile img) throws IOException {
        return uploadService.uploadImg(img);
    }

}
