package com.chm.fj.util;

import com.chm.fj.consts.ParamConst;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtil {
	public static void main(String[] args){
		String json = "{\"indexs\":[{\"id\":\"1234\",\"name\":\"陈焕明\",\"cars\":[{\"carName\":\"奔驰\",\"speed\":\"200KM/H\"},{\"carName\":\"宝马\",\"speed\":\"199KM/H\"}]},{\"id\":\"1234\",\"name\":\"范嘉敏\",\"cars\":[{\"carName\":\"劳斯莱斯\",\"speed\":\"250KM/H\"},{\"carName\":\"F1\",\"speed\":\"499KM/H\"}]}]}";
		System.out.println(getJSONForKey(JSONObject.fromObject(json), "indexs.1.cars.1.spedd"));
	}
	public static Object getJSONForKey(JSONObject json,String key){
		if(Tools.isEmpty(key)){
			return null;
		}
		String[] keys = key.split(ParamConst.SEPARATOR_DOT);
		int index = 0;
		String arrayKey = getArrayKey(keys[index]);
		int count = getArraySub(keys[index]);
		if(isJSONArray(json.get(arrayKey).getClass().toString())){
			if(isJSONArray(json.getJSONArray(arrayKey).get(count).getClass().toString())){
				return getJSONArrayValue(json.getJSONArray(arrayKey).get(count), keys, index+1);
			}else{
				return getJSONObjectValue((JSONObject) json.getJSONObject(arrayKey).get(count), keys, index+1);
			}
		}else{
			return getJSONObjectValue(json.getJSONObject(keys[index]), keys, index+1);
		}
	}
	public static Object getJSONForKey(JSONArray array ,String key){
		if(Tools.isEmpty(key)){
			return null;
		}
		String[] keys = key.split(ParamConst.SEPARATOR_DOT);
		int index = 0;
		String parKey = keys[0];
		int count = getArraySub(parKey);
		if(count ==-1){
			return array;
		}
		return isJSONArray(array.get(count).getClass().toString())?getJSONArrayValue(array.getJSONArray(count), keys, index+1):getJSONObjectValue(array.getJSONObject(count), keys, index+1);
	
	}
	
	public static Object getJSONObjectValue(JSONObject json,String[] keys,int index){
		System.out.println(json);
		if(keys.length<=(index+1)){
			return json;
		}
		return isJSONArray(json.get(keys[index]).getClass().toString())?getJSONArrayValue(json.getJSONArray(keys[index]), keys, index+1):getJSONObjectValue(json.getJSONObject(keys[index]), keys, index+1);
	}
	
	public static Object getJSONArrayValue(Object object,String[] keys,int index){
		System.out.println(object);
		if(keys.length<=(index+1)){
			return object;
		}
		String key = keys[index];
		int count = getArraySub(key);
		if(count ==-1){
			return object;
		}
		return null;
		//return isJSONArray(object.get(count).getClass().toString())?getJSONArrayValue(object.getJSONArray(count), keys, index+1):getJSONObjectValue(object.getJSONObject(count), keys, index+1);
	}
	
	public static int getArraySub(String key){
		int left = key.indexOf(ParamConst.LEFT_PARENTHESIS);
		int right = key.indexOf(ParamConst.RIGHT_PARENTHESIS);
		if(left<0||right<0){
			return -1;
		}
		return Integer.valueOf(key.substring(left+1, right));
	}
	public static String getArrayKey(String key){
		int left = key.indexOf(ParamConst.LEFT_PARENTHESIS);
		int right = key.indexOf(ParamConst.RIGHT_PARENTHESIS);
		if(left<0||right<0){
			return key;
		}
		return key.substring(0,left);
	}
	/**
	 * 判断是否为JSONArray
	 * @param clazz
	 * @return
	 */
	public static boolean isJSONArray(String clazz){
		String[] list = clazz.split(ParamConst.SEPARATOR_DOT);
		return ParamConst.CLAZZ_JSONARRAY.equals(list[list.length-1]);
	}
	/**
	 * 判断是否为JSONArray
	 * @param clazz
	 * @return
	 */
	public static boolean isJSONObject(String clazz){
		String[] list = clazz.split(ParamConst.SEPARATOR_DOT);
		return ParamConst.CLAZZ_JSONOBJECT.equals(list[list.length-1]);
	}
}
