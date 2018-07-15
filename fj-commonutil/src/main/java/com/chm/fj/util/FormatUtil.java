package com.chm.fj.util;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 格式转换类
 * 
 * @author chm
 * @data 2018/7/14 16:06
 */
public class FormatUtil {
	/**
	 * map转换为json格式
	 * @param map map格式数据
	 * @return map不为空返回json数据， 否则返回null
	 */
	public static JSONObject map2Json(Map<String, Object> map) {
		if (map == null) {
			return null;
		}
		return new JSONObject(map);//JSONObject.parseObject(map);
	}
	/**
	 * 将字符串转换为map格式
	 * @param str 字符串数据
	 * @return 字符串为空返回null,否则返回map数据
	 */
	public static Map<String,Object> str2Map(String str){
		if(CheckUtil.isEmpty(str)){
			return null;
		}
		return json2Map(str2Json(str));
	}
	/**
	 * 将字符串转换为json格式
	 * @param str 字符串数据
	 * @return 字符串为空返回null,否则返回json数据
	 */
	public static JSONObject str2Json(String str){
		if(CheckUtil.isEmpty(str)){
			return null;
		}
		return JSONObject.parseObject(str);
	}
	/**
	 * 将json对象转换为map格式，并返回map数据
	 * @param json 要转换为map格式的json数据
	 * @return json数据不为空的话，返回map格式数据，否则返回null;
	 */
	public static Map<String, Object> json2Map(JSONObject json) {
		if (json == null) {
			return null;
		}
		return (Map<String,Object>)json;
	}
	/**
	 * 将实体对象转换为json对象
	 * @param value
	 * @return
	 */
	public static <V> JSONObject entity2Json(V value){
		if(value==null){
			return null;
		}
		JSONObject object = (JSONObject) JSONObject.toJSON(value);
		return object;
	}
	/**
	 * 将map转换为对象
	 * @param map
	 * @return
	 */
	public static <V> V map2Entity(Map<String,Object> map){
		if(map==null){
			return null;
		}
		return json2Entity(map2Json(map));
	}
	/**
	 * 将json转换为对象
	 * @param json
	 * @return
	 */
	public static <V> V json2Entity(JSONObject json){
		if(json==null){
			return null;
		}
		//Object object = json.toJavaObject(json, clazz)
		return str2Entity(json.toJSONString());//(V)JSONObject.parse(json.toJSONString());
	}
	/**
	 * 将字符串转换为对象
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <V> V str2Entity(String str){
		if(CheckUtil.isEmpty(str)){
			return null;
		}
		return (V)JSONObject.parse(str);
	}
}
