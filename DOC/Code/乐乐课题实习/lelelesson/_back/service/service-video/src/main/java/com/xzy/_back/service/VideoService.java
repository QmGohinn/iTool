package com.xzy._back.service;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface VideoService {
    // 上传视频到阿里云，返回上传视频的 id
    public String uploadVideoAliyun(MultipartFile file);
    // 删除阿里云视频，根据阿里云视频 id
    public void deleteAliyunVideo(String videoSourceId);
    // 删除多个阿里云视频
    public void deleteAliyunVideos(List<String> videoIdList);
    // 根据视频 id 获取视频播放地址
    public String getPlayUrl(String videoId) throws Exception;
}