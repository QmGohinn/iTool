package com.xzy._back.service.oss.Controller;
import com.xzy._back.commonutils2.RestResult;
import com.xzy._back.service.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(description="阿里云文件管理")
@RestController
@RequestMapping("/eduoss/file")
@CrossOrigin // 跨域
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public RestResult uploadOssFile(MultipartFile file) {
        // 获取上传文件 MultipartFile
        // 返回上传到 oss 的路径
        String uploadUrl = ossService.uploadFileAvatar(file);
        return RestResult.ok().message("文件上传成功").data("url", uploadUrl);
    }
}