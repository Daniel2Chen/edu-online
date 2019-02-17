package com.huainian.eduonline.service;
/**
 * 用户接口
 * @Title:  UserService.java   
 * @Package com.huainian.eduonline.service      
 * @author: huainian.chen    
 * @date:   2019年2月17日 下午9:08:22
 * @Copyright: 2019 www.huainian.com Inc. All rights reserved.
 * @Description:
 */

import com.huainian.eduonline.bean.entity.User;

public interface UserService {
	
	User saveWechatUser(String code);
}
