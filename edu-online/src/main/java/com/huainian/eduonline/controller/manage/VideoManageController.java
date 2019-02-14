package com.huainian.eduonline.controller.manage;

import com.huainian.eduonline.bean.entity.Video;
import com.huainian.eduonline.service.VideoService;
import com.huainian.eduonline.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: VideoManageController
 * Author: huainian.chen
 * Date: 2019/2/14 18:05
 * Description: 视频管理
 */
@RestController
@RequestMapping("manage/v1/video")
public class VideoManageController {
    @Autowired
    private VideoService videoService;
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
