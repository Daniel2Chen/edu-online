package com.huainian.eduonline.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huainian.eduonline.bean.entity.User;
import com.huainian.eduonline.config.WechatConfig;
import com.huainian.eduonline.mapper.UserMapper;
import com.huainian.eduonline.service.UserService;
import com.huainian.eduonline.utils.HttpUtils;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private WechatConfig wechatConfig;
	@Autowired UserMapper userMapper;
	@Override
	public User saveWechatUser(String code) {
		String accessTokenUrl = String.format(wechatConfig.getOpenAccessTokenUrl(), 
				wechatConfig.getOpenAppId(),wechatConfig.getOpenAppSecret(),code);
		Map<String, Object> baseMap = HttpUtils.doGet(accessTokenUrl);
		if (baseMap == null || baseMap.isEmpty()) {
			return null;
		}
		String accessToken = (String) baseMap.get("access_token");
		String openId = (String) baseMap.get("openid");
		User dbUser = userMapper.findByOpenId(openId);
		if (dbUser != null) {
			return dbUser;
		}
		String userInfoUrl = String.format(wechatConfig.getOpenUserInfoUrl(), 
				accessToken,openId);
		Map<String, Object> baseUserMap = HttpUtils.doGet(userInfoUrl);
		if (baseUserMap == null || baseUserMap.isEmpty()) {
			return null;
		}
		String nickname = (String)baseUserMap.get("nickname");

        Double sexTemp  = (Double) baseUserMap.get("sex");
        int sex = sexTemp.intValue();
        String province = (String)baseUserMap.get("province");
        String city = (String)baseUserMap.get("city");
        String country = (String)baseUserMap.get("country");
        String headimgurl = (String)baseUserMap.get("headimgurl");
        StringBuilder sb = new StringBuilder(country).append("||").append(province).append("||").append(city);
        String finalAddress = sb.toString();
        try {
        	nickname = new String(nickname.getBytes("ISO-8859-1"), "UTF-8");
            finalAddress = new String(finalAddress.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
        User user = new User();
        user.setName(nickname);
        user.setHeadImg(headimgurl);
        user.setCity(finalAddress);
        user.setOpenid(openId);
        user.setSex(sex);
        user.setCreateTime(new Date());
        userMapper.save(user);
		return user;
	}

}
