package com.huainian.eduonline.service.impl;

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
    public List<?> getVideoList() {
        return videoMapper.getAll();
    }
}
