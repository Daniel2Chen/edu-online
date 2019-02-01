/**
 * @description
 * @author huainian.chen
 * @date 2019年1月31日 
 */
package com.huainian.eduonline.config;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;
@Configuration
@PropertySource(value="classpath:application.properties")
@ConfigurationProperties(prefix="wxpay")
@Data
public class WeChatConfig implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1283347762892743075L;
	private String appId;
	private String appSecret;

}
