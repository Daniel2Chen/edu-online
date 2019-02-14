package com.huainian.eduonline.controller;

import com.huainian.eduonline.service.VideoService;
import com.huainian.eduonline.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * FileName: VideoController
 * Author: huainian.chen
 * Date: 2019/2/12 14:21
 * Description: 视频
 */
@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    public JsonData getVideoList(){
        List<?> videoList = videoService.getVideoList();
        return videoList.size() > 0 ? JsonData.builderSuccess().data(videoList):JsonData.builderFail();
    }
}
