package com.xzy._back.controller;

import com.xzy._back.commonutils2.RestResult;
import com.xzy._back.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Api(description = "上传视频到阿里云")
@RestController
@RequestMapping("/eduvideo/video")
@CrossOrigin  // 跨域
public class VideoController {
    @Autowired
    private VideoService videoService;
    @ApiOperation(value = "上传视频到阿里云")
    @PostMapping("uploadAliyunVideo")
    public RestResult uploadOssFile(MultipartFile file) {
        // 上传视频到阿里云，返回上传视频的 id
        String videoId = videoService.uploadVideoAliyun(file);
        String playUrl = "";
        try {
            playUrl = videoService.getPlayUrl(videoId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestResult.ok().data("videoId", videoId).data("playUrl", playUrl);
    }

    @ApiOperation(value = "根据视频 id 删除阿里云视频")
    @DeleteMapping("deleteAliyunVideo/{videoSourceId}")
    public RestResult deleteAliyunVideo(
            @ApiParam(name = "videoSourceId", value = "阿里云视频 ID", required = true)
            @PathVariable String videoSourceId) {
        videoService.deleteAliyunVideo(videoSourceId);
        return RestResult.ok();
    }
    @ApiOperation(value = "删除多个阿里云视频")
    @DeleteMapping("delete-batch")
    public RestResult deleteAliyunVideos(@RequestParam("videoIdList") List<String> videoIdList) {
        videoService.deleteAliyunVideos(videoIdList);
        return RestResult.ok();
    }
}