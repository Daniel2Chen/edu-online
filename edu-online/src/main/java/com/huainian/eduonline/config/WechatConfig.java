/**
 * @description
 * @author huainian.chen
 * @date 2019年1月31日 
 */
package com.huainian.eduonline.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;
@Configuration
@PropertySource(value="classpath:application.properties")
@Data
public class WechatConfig implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1283347762892743075L;
	/**
	 * 微信开放平台二维码链接
	 */
	private final static String OPEN_QRCODE_URL = "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s#wechat_redirect";
	/**
	 * 开放平台获取 access_token链接
	 */
	private final static String OPEN_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
	/**
	 * 开放平台获取用户信息链接
	 */
	private final static String OPEN_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
	/**
	 * 公众号appid
	 */
	@Value("${wxpay.appid}")
	private String appId;
	/**
	 * 公众号秘钥
	 */
	@Value("${wxpay.appsecret}")
	private String appSecret;
	/**
	 * 开放平台appid
	 */
	@Value("${wxopen.appid}")
	private String openAppId;
	/**
	 * 开放平台appsecret
	 */
	@Value("${wxopen.appsecret}")
	private String openAppSecret;
	/**
	 * 开放平台回调url
	 */
	@Value("${wxopen.redirect_url}")
	private String openRedirectUrl;
	
	/**
	 * @return the openQrcodeUrl
	 */
	public static String getOpenQrcodeUrl() {
		return OPEN_QRCODE_URL;
	}
	/**
	 * @return the openAccessTokenUrl
	 */
	public static String getOpenAccessTokenUrl() {
		return OPEN_ACCESS_TOKEN_URL;
	}
	/**
	 * @return the openUserInfoUrl
	 */
	public static String getOpenUserInfoUrl() {
		return OPEN_USER_INFO_URL;
	}

}
