/**
 * @description
 * @author huainian.chen
 * @date 2019年1月31日 
 */
package com.huainian.eduonline.utils;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 */
public class JsonData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8905967384545875593L;
	
	private String message;
	
	private String code;
	
	private Object data;
	
	private HashMap<String, Object> multiData = new HashMap<>();
	
	public JsonData() {
		
	}
	
	public JsonData(String code,String message,Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
	public static JsonData buildSuccess() {
		JsonData jsonData = new JsonData();
		jsonData.code = "200";
		jsonData.message = "操作成功！";
		return jsonData;
	}
	public static JsonData buildFailure() {
		JsonData jsonData = new JsonData();
		jsonData.code = "201";
		jsonData.message ="服务器异常";
		return  jsonData;
	}
	public static JsonData data(Object object) {
		JsonData jsonData = new JsonData();
		jsonData.code = "200";
		jsonData.message = "操作成功！";
		jsonData.data = object;
		return jsonData;
	}
	public static JsonData multiData(String str,Object object) {
		JsonData jsonData = new JsonData();
		jsonData.code = "200";
		jsonData.message = "操作成功！";
		jsonData.multiData.put(str, object);
		return jsonData;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the multiData
	 */
	public HashMap<String, Object> getMultiData() {
		return multiData;
	}

	/**
	 * @param multiData the multiData to set
	 */
	public void setMultiData(HashMap<String, Object> multiData) {
		this.multiData = multiData;
	}
	
}
