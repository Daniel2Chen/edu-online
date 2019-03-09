package com.huainian.eduonline.utils;

import java.security.MessageDigest;
import java.util.UUID;

public class CommonUtils {
	
	/**
	 * 
	 * @Description: 生成32位随机UUID字符串
	 * @Title: getUUID      
	 * @return           
	 * @throws
	 */
	public static String getUUID() {
		
		return UUID.randomUUID().toString().replaceAll("-", "")
				.substring(0, 32);
	}
	
	/**
	 * 
	 * @Description: md5加密
	 * @Title: MD5      
	 * @param data
	 * @return           
	 * @throws
	 */
	public static String MD5(String data) {
		
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte [] byteArray = md5.digest(data.getBytes("UTF-8"));
			StringBuffer sb = new StringBuffer();
			for (byte b : byteArray) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString().toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
