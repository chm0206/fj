package com.chm.fj.Result;

import java.util.HashMap;
import java.util.Map;

import com.chm.fj.consts.StatusCodeConst;

import net.sf.json.JSONObject;

public class ResultInfo {

	public static void main(String[] args) {
		Integer code = 400;
		//JSONObject data = new JSONObject();
		Map<String,Object> data= new HashMap<>();
		data.put("userID", "chm");
		ResultInfo ri = new ResultInfo(code,data);
		System.out.println(ri);
	}
	/**
	 * 生成默认状态码
	 */
	public ResultInfo() {
		this(StatusCodeConst.CODE_NORMAL);
	}
	/**
	 * 添加状态码
	 * @param code
	 */
	public ResultInfo(Integer code) {
		this(code, new JSONObject());
	}
	/**
	 * 添加为Map格式的返回数据及默认状态码
	 * @param map map格式的返回数据
	 */
	public ResultInfo(Map<String, Object> map) {
		this(StatusCodeConst.CODE_NORMAL, map);
	}
	/**
	 * 添加为Map格式的返回数据及状态码
	 * @param code 状态码
	 * @param map map格式的返回数据
	 */
	public ResultInfo(Integer code,Map<String, Object> map){
		this(code, JSONObject.fromObject(map));
	}
	/**
	 * 添加要返回的数据及默认状态码
	 * @param object 要返回的数据
	 */
	public ResultInfo(JSONObject object) {
		this(StatusCodeConst.CODE_NORMAL, object);
	}
	/**
	 * 添加状态码及返回数据  
	 * @param code 状态码
	 * @param object 要返回数据
	 */
	public ResultInfo(Integer code, JSONObject object) {
		this.code = code;
		this.data = object;
	}
	/**
	 * 添加状态说明及默认状态码
	 * @param msg 状态说明
	 */
	public ResultInfo(String msg) {
		this(StatusCodeConst.CODE_NORMAL, msg);
	}
	/**
	 * 添加状态码及状态说明
	 * @param code 状态码
	 * @param msg 状态说明
	 */
	public ResultInfo(Integer code,String msg){
		this(code);
		JSONObject object = new JSONObject();
		object.put("msg", msg);
		this.data = object;
	}

	private Integer code;

	private JSONObject data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}
	public void setData(Map<String,Object> map){
		this.data = JSONObject.fromObject(map);
	}
	
	public void putAll(Map<String,Object> map){
		this.data.putAll(map);
	}
	public void putAll(JSONObject object){
		this.data.putAll(object);
	}
	public void put(String key,String value){
		this.data.put(key, value);
	}
	public void put(String str){
		
	}

	@Override
	public String toString() {
		return "ResultInfo [code=" + code + ", data=" + data + "]";
	}

}
