package com.huainian.eduonline.utils;

import java.io.Serializable;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * FileName: VO
 * Author: huainian.chen
 * Date: 2019/2/15 17:47
 * Description: VO工具类
 */
public class VO extends HashMap<String,Object> implements Serializable {

    private static final long serialVersionUID = 6208106294465026862L;

    protected VO(){}

    public static  VO init(){
        return new VO();
    }

    public static  VO init(String key,Object value){
        return (new VO()).put(key, value);
    }

    public VO put(String key,Object value){
        super.put(key,value == null ? "" : value);
        return this;
    }
    public VO remove(String key) {
        super.remove(key);
        return this;
    }

    public VO removeAll() {
        super.clear();
        return this;
    }

    public Object getValue(String key) {
        return super.get(key);
    }

    public <T> T toObject(Class<T> clazz) {
    	ObjectMapper mapper = new ObjectMapper();
        try {
        	return mapper.readValue(this.toString(), clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isEmpty() {
        return super.isEmpty();
    }

    public String toString() {
    	ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
    }

}
