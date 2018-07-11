package com.chm.fj.util;

import com.chm.fj.consts.ParamConst;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtil {

	public static void main(String[] args) {
		String json = "{\"indexs\":[{\"id\":\"1234\",\"name\":\"chm\",\"cars\":[{\"name\":\"benz\",\"speed\":\"200km/h\"},{\"name\":\"bmw\",\"speed\":\"250km/h\"}]},{\"id\":\"567\",\"name\":\"fj\",\"cars\":[{\"name\":\"法拉利\",\"speed\":\"300km/h\"},{\"name\":\"F1\",\"speed\":\"500km/h\"}]}]}";
		System.out.println(getJSON(JSONObject.fromObject(json), "indexs[0].cars[0].name"));

	}

	public static Object getJSON(JSONObject json, String key) {
		return getJSONForObject(json, key.split(ParamConst.SEPARATOR_DOT), 0);
	}
	/**
	 * 获取到Object类型的json对象
	 * @param object
	 * @param keys
	 * @param index
	 * @return
	 */
	public static Object getJSONForObject(JSONObject object, String[] keys, int index) {
		if (keys.length < index + 1) {
			return object;
		}

		String keyAll = keys[index];
		String key = getKey(keyAll);
		if (isArray(object.get(key).getClass().toString())) {// 判断是否为JSONArray
			int count = getKeySub(keyAll);
			if (keys.length == index + 1) {
					return ((JSONArray) object.get(key)).get(count);
			} else {
					return getJSONForObject(((JSONArray) object.get(key)).getJSONObject(count), keys, index + 1);
			}
		} else {
			if (keys.length == index + 1) {
				return object.get(key);
			} else {
				return getJSONForObject((JSONObject) object.get(key), keys, index + 1);
			}
		}
	}
	/**
	 * 获取数组Key值
	 * <pro>
	 * name[1]，获取到的数据是name,如果为name,则返回name
	 * </pro>
	 * @param key 
	 * @return
	 */
	public static String getKey(String key) {
		int left = key.indexOf(ParamConst.LEFT_PARENTHESIS);
		int right = key.indexOf(ParamConst.RIGHT_PARENTHESIS);
		if (left < 0 || right < 0) {
			return key;
		}
		return key.substring(0, left);
	}
	/**
	 * 获取数组下标
	 * @param key 数组名称
	 * @return 获取到数组下标，返回下载值，否则返回-1
	 */
	public static int getKeySub(String key) {
		int left = key.indexOf(ParamConst.LEFT_PARENTHESIS);
		int right = key.indexOf(ParamConst.RIGHT_PARENTHESIS);
		if (left < 0 || right < 0) {
			return -1;
		}
		return Integer.valueOf(key.substring(left + 1, right));
	}

	/**
	 * 判断是否为JSON数组
	 * @param clazz class名称完全路径
	 * @return
	 */
	public static boolean isArray(String clazz) {
		String[] list = clazz.split(ParamConst.SEPARATOR_DOT);
		return "JSONArray".equals(list[list.length - 1]);
	}

}