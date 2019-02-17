package com.huainian.eduonline.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.huainian.eduonline.bean.entity.User;


public interface UserMapper {
	
	/**
     * 根据openid查找
     * @param openid
     * @return
     */
    @Select("select * from user where openid = #{openid}")
    User findByOpenId(@Param("openid") String openid);
    /**
     * 保存用户新
     * @param user
     * @return
     */
    @Insert("INSERT INTO `user` ( `openid`, `name`, `head_img`, `phone`, `sign`, `sex`, `city`, `create_time`)" +
            "VALUES" +
            "(#{openid},#{name},#{headImg},#{phone},#{sign},#{sex},#{city},#{createTime});")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int save(User user);
}
