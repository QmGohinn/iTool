package com.xzy._back.client;

import com.xzy._back.commonutils2.RestResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@FeignClient(name = "service-video", fallback = VideoClientImpl.class)
@Component
public interface VideoClient {
    // 定义调用方法的路径
    // 根据视频 id 删除阿里云对应的视频
    @DeleteMapping("/eduvideo/video/deleteAliyunVideo/{videoSourceId}")
    public RestResult deleteAliyunVideo(
            @PathVariable("videoSourceId") String videoSourceId);
    // 定义调用删除多个视频的方法
    // 删除多个阿里云的视频
    @DeleteMapping("/eduvideo/video/delete-batch")
    public RestResult deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}