package com.huainian.eduonline.service.impl;

import com.github.pagehelper.PageHelper;
import com.huainian.eduonline.bean.entity.Video;
import com.huainian.eduonline.mapper.VideoMapper;
import com.huainian.eduonline.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * FileName: VideoServiceImpl
 * Author: huainian.chen
 * Date: 2019/2/12 14:20
 * Description: 视频
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;
    @Override
    public List<Video> getVideoList(int offset,int limit) {
        PageHelper.startPage(offset,limit,"create_time asc");
        return videoMapper.getAll();
    }

    @Override
    public Video getVideoById(Integer id) {
        return videoMapper.getVideoById(id);
    }

    @Override
    public int deleteVideo(Integer id) {
        return videoMapper.deleteVideoById(id);
    }

    @Override
    public int updateVideo(Video video) {
        return videoMapper.updateVideo(video);
    }

    @Override
    public int insertVideo(Video video) {
        return videoMapper.insertVideo(video);
    }
}
