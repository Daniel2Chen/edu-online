package com.huainian.eduonline.controller;

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
    public JsonData getVideoList(){
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

    @PutMapping("updateVideo")
    public JsonData updateVideo(Video video){
        try {
            int rows = videoService.updateVideo(video);
            return  rows>0 ? JsonData.builderSuccess():JsonData.builderFail();
        }catch (Exception e){
            e.printStackTrace();
            return  JsonData.builderFail();
        }
    }
    @PostMapping("insertVideo")
    public JsonData insertVideo(Video video){
        try {
            int rows = videoService.insertVideo(video);
            return rows>0 ? JsonData.builderSuccess():JsonData.builderFail();
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.builderFail();
        }
    }

    public JsonData deleteVideo(Integer id){
        try {
            int rows = videoService.deleteVideo(id);
            return rows>0 ? JsonData.builderSuccess():JsonData.builderFail();
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.builderFail();
        }
    }
}
