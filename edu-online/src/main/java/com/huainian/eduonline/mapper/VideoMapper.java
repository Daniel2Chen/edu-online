package com.huainian.eduonline.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * FileName: VideoMapper
 * Author: huainian.chen
 * Date: 2019/2/12 14:11
 * Description: 视频mapper
 */
public interface VideoMapper {

    @Select("select * from video")
    @Results({
            @Result(column = "create_time",property = "createTime")
    })
    List<?> getAll();
}
