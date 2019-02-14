package com.huainian.eduonline.controller.api;

import com.huainian.eduonline.bean.entity.Video;
import com.huainian.eduonline.service.VideoService;
import com.huainian.eduonline.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * FileName: VideoController
 * Author: huainian.chen
 * Date: 2019/2/12 14:21
 * Description: 视频
 */
@RestController
@RequestMapping("api/v1/video")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @GetMapping("/listVideo")
    public JsonData getVideoList(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                 @RequestParam(value = "size",defaultValue = "10")Integer size){
        List<Video> videoList = videoService.getVideoList();
        return (videoList !=null && videoList.size() > 0) ? JsonData.builderSuccess().data(videoList):JsonData.builderFail();
    }
    @GetMapping("/getVideoById")
    public JsonData getVideoById(Integer id){
        try {
            Video video = videoService.getVideoById(id);
            return JsonData.builderSuccess().data(video);
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.builderFail();
        }
    }
}
