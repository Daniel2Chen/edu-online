package com.huainian.eduonline.provider;

import com.huainian.eduonline.bean.entity.Video;
import org.apache.ibatis.jdbc.SQL;

/**
 * FileName: VideoSqlProvider
 * Author: huainian.chen
 * Date: 2019/2/15 10:04
 * Description: 视频动态SQL拼接
 */
public class VideoSqlProvider {
    public String updateVideo(final Video video){
        return new SQL(){{
            UPDATE("video");
            if (video.getTitle() != null){
                SET("title=#{title}");
            }
            if (video.getSummary() != null){
                SET("summary=#{summary}");
            }
            WHERE("id=#{id}");
        }}.toString();
    }
}
