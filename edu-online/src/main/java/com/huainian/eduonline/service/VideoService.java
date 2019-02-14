package com.huainian.eduonline.service;

import com.huainian.eduonline.bean.entity.Video;

import java.util.List;

/**
 * FileName: VideoService
 * Author: huainian.chen
 * Date: 2019/2/12 14:18
 * Description: 视频service
 */
public interface VideoService {
    /**
     * 获取视频列表
     * @return
     */
    List<Video> getVideoList();

    /**
     * 通过id获取视频
     * @param id
     * @return
     */
    Video getVideoById(Integer id);

    /**
     * 删除视频
     * @param id
     * @return
     */
    int deleteVideo(Integer id);

    /**
     * 修改视频
     * @param video
     * @return
     */
    int updateVideo(Video video);

    /**
     * 添加视频
     * @param video
     * @return
     */
    int insertVideo(Video video);
}
