/**
 * @description
 * @author huainian.chen
 * @date 2019年1月31日 
 */
package com.huainian.eduonline.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	private int code;
	
	private Object data;
	
	private HashMap<String, Object> multiData = new HashMap<>();
	
	protected JsonData() {
		
	}
	
	protected JsonData(ResultOptions option,String ... messages) {
		String msg = option.getMsg();
		if (messages != null && messages.length > 0){
			msg = messages[0];
		}
		this.code = option.getCode();
		this.message = msg;
	}
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}


	public HashMap<String, Object> getMultiData() {
		return multiData;
	}


	public void setMultiData(HashMap<String, Object> multiData) {
		this.multiData = multiData;
	}
	public JsonData resetOption(ResultOptions option,String ... messages){
		this.validataOption(option);
		String rmsg = option.getMsg();
		if (messages != null && messages.length > 0){
			rmsg = messages[0];
		}
		this.code = option.getCode();
		this.message = rmsg;
		return this;
	}
	public JsonData data(String key,Object value){
		this.multiData.put(key,value);
		return this;
	}
	public JsonData data(Object value){
		this.data = value;
		return this;
	}
	public static  JsonData builder(ResultOptions option,String ... message){
		return new JsonData(option,message);
	}
	public static JsonData builderSuccess(String ... message){
		return new JsonData(DefaultResultOptions.SUCCESS,message);
	}
	public static JsonData builderException(String ... message){
		return new JsonData(DefaultResultOptions.EXCEPTION,message);
	}
	public static JsonData builderMissParameter(String ... message){
		return  new JsonData(DefaultResultOptions.MISSPARAMTER,message);
	}
	public static JsonData builderFail(String ... message){
		return  new JsonData(DefaultResultOptions.FAIL,message);
	}
	public static JsonData builderOutTime(String ... message){
		return  new JsonData(DefaultResultOptions.OUTTIME,message);
	}
	public String toString(){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

	private void validataOption(ResultOptions option){
		if (option == null || ! option.getClass().isEnum()){
			throw  new RuntimeException("ResultOptions must be an enumeration and implement ResultOptions.");
		}
	}
}
