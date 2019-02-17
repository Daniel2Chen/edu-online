package com.huainian.eduonline.utils;
/**
 * 封装 http get post
 * @Title:  HttpUtils.java   
 * @Package com.huainian.eduonline.utils      
 * @author: huainian.chen    
 * @date:   2019年2月17日 下午3:58:13
 * @Copyright: 2019 www.huainian.com Inc. All rights reserved.
 * @Description:
 */

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class HttpUtils {
	
	private static final Gson gson = new Gson();
	
	/**
	 * get方法
	 * @Title: doGet      
	 * @param: @param url
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 * @Description:
	 */
	public static Map<String, Object> doGet(String url) {
		
		Map<String, Object> map = new HashMap<>();
		//创建HttpClient
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//请求配置
		RequestConfig requestConfig = requestConfig();
				
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requestConfig);
		
		try {
			//发起请求，获取结果
			HttpResponse httpResponse = httpClient.execute(httpGet);
			//结果处理
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				String jsonResult = EntityUtils.toString(httpResponse.getEntity());
				map = gson.fromJson(jsonResult, map.getClass());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				httpClient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	/**
	 * 封装post方法
	 * @Title: doPost      
	 * @param: @param url
	 * @param: @param data
	 * @param: @return      
	 * @return: String      
	 * @throws
	 * @Description:
	 */
	public static String doPost(String url,String data) {
		//创建HttpClient
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//配置请求
		RequestConfig requestConfig = requestConfig();
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type","text/html; chartset=UTF-8");
		httpPost.setConfig(requestConfig);
		if (data !=null && data instanceof String) {
			StringEntity entity = new StringEntity(data,"UTF-8");
			httpPost.setEntity(entity);
		}
		try {
			//发起请求，获取结果
			HttpResponse httpResponse = httpClient.execute(httpPost);
			//结果处理
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				String result = EntityUtils.toString(httpResponse.getEntity());
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				httpClient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 请求配置
	 * @Title: requestConfig      
	 * @param: @return      
	 * @return: RequestConfig      
	 * @throws
	 * @Description:
	 */
	private static RequestConfig requestConfig() {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000)//设置连接超时
				.setConnectionRequestTimeout(5000)//设置请求超时
				.setSocketTimeout(5000)
				.setRedirectsEnabled(true)//允许自动重定向
				.build();
		return requestConfig;
	}
	
}
