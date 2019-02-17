package com.huainian.eduonline.controller.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huainian.eduonline.bean.entity.User;
import com.huainian.eduonline.config.WechatConfig;
import com.huainian.eduonline.service.UserService;
import com.huainian.eduonline.utils.JWTUtils;
import com.huainian.eduonline.utils.JsonData;


@RestController
@RequestMapping("/api/v1/wechat")
public class WechatController {

    @Autowired
    private WechatConfig weChatConfig;
    @Autowired
    private UserService UserService;
    /**
     * 拼装微信扫一扫登录url
     * @return
     */
    @GetMapping("loginByWechat")
    @ResponseBody
    public JsonData loginByWechat(@RequestParam(value = "access_page",required = true)String accessPage) throws UnsupportedEncodingException {

        String redirectUrl = weChatConfig.getOpenRedirectUrl(); //获取开放平台重定向地址

        String callbackUrl = URLEncoder.encode(redirectUrl, "UTF-8"); //进行编码

        String qrcodeUrl = String.format(weChatConfig.getOpenQrcodeUrl(),weChatConfig.getOpenAppId(),callbackUrl,accessPage);

        return JsonData.builderSuccess().data(qrcodeUrl);
    }
    @GetMapping("/user/callback")
    public void saveUser(@RequestParam(value="code") String code,@RequestParam(value="state") String state,HttpServletResponse response) throws UnsupportedEncodingException, IOException {
    	
    	User loginUser = UserService.saveWechatUser(code);
    	if (loginUser != null && loginUser.getOpenid() != null) {
			String token = JWTUtils.geneJsonWebToken(loginUser);
			// state 当前用户的页面地址，需要拼接 http://  这样才不会站内跳转

            response.sendRedirect(state+"?token="+token+"&head_img="+loginUser.getHeadImg()+"&name="+URLEncoder.encode(loginUser.getName(),"UTF-8"));
		}
	}
}
