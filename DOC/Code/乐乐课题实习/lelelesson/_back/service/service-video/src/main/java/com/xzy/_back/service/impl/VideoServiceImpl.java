package com.xzy._back.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.xzy._back.service.base.exceptionhandler.MyException;
import com.xzy._back.service.VideoService;
import com.xzy._back.utils.ConstantVideoUtils;
import com.xzy._back.utils.InitObject;
import com.xzy._back.utils.InitVideoClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@Service
public class VideoServiceImpl implements VideoService {
    // 上传视频到阿里云，返回上传视频的 id
    @Override
    public String uploadVideoAliyun(MultipartFile file) {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取上传之后文件名称
        String title = fileName.substring(0, fileName.lastIndexOf("."));
        InputStream inputStream = null;
        try {
            // 获取上传文件输入流
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return testUploadStream(ConstantVideoUtils.ACCESS_KEY_ID, ConstantVideoUtils.ACCESS_KEY_SECRET, title, fileName, inputStream);
    }
    // 删除阿里云视频，根据阿里云视频 id
    @Override
    public void deleteAliyunVideo(String videoSourceId) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVideoClient.initVodClient(ConstantVideoUtils.ACCESS_KEY_ID, ConstantVideoUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(videoSourceId);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(20001, "删除视频失败");
        }
    }
    // 删除多个阿里云视频
    @Override
    public void deleteAliyunVideos(List<String> videoIdList) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVideoClient.initVodClient(ConstantVideoUtils.ACCESS_KEY_ID, ConstantVideoUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //videoIdList值转换成 1,2,3
            String videoIds = StringUtils.join(videoIdList.toArray(), ",");
            //向request设置视频id
            request.setVideoIds(videoIds);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
        }catch(Exception e) {
            e.printStackTrace();
            throw new MyException(20001,"删除视频失败");
        }
    }
    // 根据视频 id 获取视频播放地址
    @Override
    public String getPlayUrl(String videoId) throws Exception {
        // 1、创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient(ConstantVideoUtils.ACCESS_KEY_ID, ConstantVideoUtils.ACCESS_KEY_SECRET);
        // 2、创建获取视频地址 request 和 response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        // 3、向 request 对象里面设置视频 id
        request.setVideoId(videoId);
        // 4、调用初始化对象里面的方法，传递 request，获取数据
        response = client.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        // 播放地址
        String playURL = "";
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            playURL = playInfo.getPlayURL();
        }
        return playURL;
    }
    /**
     * 流式上传接口
     *
     * @param accessKeyId：Bucket 的 ID
     * @param accessKeySecret：Bucket 的 秘钥
     * @param title：上传之后文件名称
     * @param fileName：本地文件路径和名称
     * @param inputStream：上传文件输入流
     */
    private static String testUploadStream(String accessKeyId, String accessKeySecret, String title, String fileName, InputStream inputStream)
    {
        UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName, inputStream);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        String VideoId = null;
        if (response.isSuccess()) {
            VideoId = response.getVideoId();
        } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            VideoId = response.getVideoId();
        }
        return VideoId;
    }
}