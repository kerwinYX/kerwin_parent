package com.kerwin.oss.controller;

import com.kerwin.commonutils.R;
import com.kerwin.oss.service.OssService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss")
@CrossOrigin
public class OssController {

    private final OssService ossService;

    public OssController(OssService ossService) {
        this.ossService = ossService;
    }

    //上传头像的方法
    @PostMapping("fileosss")
    public R uploadOssFile(MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
    @PostMapping("file111")
    public R ok(MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        return R.ok().data("url","url");
    }
}
