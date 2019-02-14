package com.huainian.eduonline.mapper;

import com.huainian.eduonline.bean.entity.Video;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * FileName: VideoMapper
 * Author: huainian.chen
 * Date: 2019/2/12 14:11
 * Description: 视频mapper
 */
public interface VideoMapper {

    @Select("select * from video")
    List<Video> getAll();

    @Select("select * from video where id = #{id}")
    Video getVideoById(Integer id);

    @Delete("delete from video where id = #{id}")
    int deleteVideoById(Integer id);

    @Update("update video set title = #{title} where id = #{id}")
    int updateVideo(Video video);

    @Insert("INSERT INTO `edu_online`.`video`" +
            "( `title`)" +
            " VALUES (#{title});")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    int insertVideo(Video video);
}
