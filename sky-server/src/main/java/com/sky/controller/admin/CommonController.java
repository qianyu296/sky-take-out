package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 通用接口
 * */
@Api(tags = "通用接口")
@RestController
@RequestMapping("/admin/common")
@Slf4j
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;
    /**
     * 文件上传
     * @param file
     * @return
     * */
    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public Result<String> upload(@RequestBody MultipartFile file){
        log.info("文件上传:{}",file);
        String name = file.getOriginalFilename();
        // 获取原本文件的后缀名
        String endName = ".";
        assert name != null;
        endName += name.substring(name.lastIndexOf("."));
        // 生成一个UUID作为上传文件的文件名,防止上传文件的文件名冲突导致文件覆盖
        String filename = UUID.randomUUID() + endName;
        // 上传之后的访问地址
        String fileUrl = "";
        try {
            // 文件上传
            fileUrl = aliOssUtil.upload(file.getBytes(), filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(fileUrl);
    }
}
