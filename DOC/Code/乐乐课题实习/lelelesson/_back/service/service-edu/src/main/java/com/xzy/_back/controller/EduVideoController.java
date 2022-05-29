package com.xzy._back.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xzy._back.client.VideoClientImpl;
import com.xzy._back.commonutils2.RestResult;
import com.xzy._back.model.EduVideo;
import com.xzy._back.service.EduVideoService;
import com.xzy._back.service.base.exceptionhandler.MyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author 吴斌
 * @since 2022-01-06
 */
@Api(description = "课程小节管理")
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;
    private VideoClientImpl videoClient;

    @ApiOperation(value = "添加课程小节")
    @PostMapping("addVideo")
    public RestResult addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return RestResult.ok();
    }

    // TODO 需要完善，删除课程小节时，同事删除其中的视频
    @ApiOperation(value = "删除课程小节信息")
    @DeleteMapping("{id}")
    public RestResult deleteVideo(
            @ApiParam(name = "id", value = "课程小节 ID", required = true)
            @PathVariable String id) {
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();

        RestResult result = videoClient.deleteAliyunVideo(videoSourceId);
        videoService.removeById(id);
        return RestResult.ok();
    }
    @ApiOperation(value = "根据课程小节 ID 查询课程小节信息")
    @GetMapping("getVideoInfo/{id}")
    public RestResult getVideoInfo(
            @ApiParam(name = "id", value = "课程小节 ID", required = true)
            @PathVariable String id) {
        EduVideo eduVideo = videoService.getById(id);
        return RestResult.ok().data("eduVideo", eduVideo);
    }
    @ApiOperation(value = "修改课程小节信息")
    @PostMapping("updateVideo")
    public RestResult updateVideo(@RequestBody EduVideo eduVideo) {
        videoService.updateById(eduVideo);
        return RestResult.ok();
    }
}

