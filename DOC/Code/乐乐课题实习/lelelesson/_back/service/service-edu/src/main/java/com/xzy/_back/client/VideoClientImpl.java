package com.xzy._back.client;

import com.xzy._back.commonutils2.RestResult;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class VideoClientImpl implements VideoClient {
    @Override
    public RestResult deleteAliyunVideo(String videoSourceId) {
        return RestResult.error().message("删除视频出错了！");
    }
    @Override
    public RestResult deleteBatch(List<String> videoIdList) {
        return RestResult.error().message("删除多个视频出错了！");
    }
}