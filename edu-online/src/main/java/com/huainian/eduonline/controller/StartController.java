/**
 * @description
 * @author huainian.chen
 * @date 2019年1月31日 
 */
package com.huainian.eduonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huainian.eduonline.config.WeChatConfig;
import com.huainian.eduonline.utils.JsonData;

@RestController
@RequestMapping("/start")
public class StartController {
	@Autowired
	private WeChatConfig weChatConfig;
	@RequestMapping("/hello")
	public JsonData start() {
		return JsonData.data(weChatConfig.getAppId());
	}
}
