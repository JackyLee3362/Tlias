package com.jacky.controller;

import com.jacky.pojo.Result;
import com.jacky.utils.AliOSSUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliOSSUtil aliOSSUtil;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception {
        String url = aliOSSUtil.upload(image);
        log.info(url);
        return Result.success(url);
    }

    @Deprecated
    @PostMapping("/upload_local")
    public Result upload2Local(MultipartFile image) throws IOException {
        // 已经不推荐使用
        String originalFilename = image.getOriginalFilename();
        log.info("文件名是{}", originalFilename);
        int index = originalFilename.lastIndexOf('.');
        index = index >= 0 ? index : originalFilename.length();
        String suffix = originalFilename.substring(index);

        File file = new File(String.format("D:\\tmp\\%s.%s", UUID.randomUUID(), suffix));
        image.transferTo(file);
        return null;
    }

}
