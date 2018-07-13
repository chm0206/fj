package com.chm.fj.util;

import java.util.ArrayList;
import java.util.List;

import com.chm.fj.consts.ParamConst;
import com.chm.fj.consts.StatusCodeConst;
import com.chm.fj.exception.MyException;
import com.chm.fj.other.DateUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtil {

	public static void main(String[] args) {
		String json = "{\"indexs\":[{\"id\":\"1234\",\"name\":\"chm\",\"cars\":[{\"name\":\"benz\",\"speed\":\"200km/h\"},{\"name\":\"bmw\",\"speed\":\"250km/h\"}]},{\"id\":\"567\",\"name\":\"fj\",\"cars\":[{\"name\":\"法拉利\",\"speed\":\"300km/h\"},{\"name\":\"F1\",\"speed\":\"500km/h\"}]}]}";
		JSONObject object = JSONObject.fromObject(json);
		System.out.println(object);
		System.out.println(DateUtil.getNow());
		System.out.println(getJSON(object, "indexs[0].cars[1].name"));
		System.out.println(DateUtil.getNow());

	}

	public static Object getJSON(JSONObject json, String key) {
		return getJSONForObject(json, key.split(ParamConst.SEPARATOR_DOT), 0);
	}

	/**
	 * 获取到Object类型的json对象
	 * 
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
		List<Integer> sub = getParenthesisSub(key);
		switch (sub.size()) {
		case 0:
			return key;
		case 2:
			return key.substring(0, sub.get(0));
		default:
			System.out.println("字符串 \""+key+"\" 格式错误");
			throw new MyException();
			//return StatusCodeConst.ERROR;
		}
//		int left = key.indexOf(ParamConst.LEFT_PARENTHESIS);
//		int right = key.indexOf(ParamConst.RIGHT_PARENTHESIS);
//		boolean isLeft = left<0?false:true;	//是否存在左括号
//		boolean isRight = right<0?false:true;//是否存在右括号
//		if (!isLeft && !isRight) {	//不存在左括号并且不存在右括号
//			return key;
//		}else if(isLeft && isRight){	//存在左括号并且存在右括号
//			return key.substring(0, left);
//		}
//		return StatusCodeConst.ERROR;
	}

	/**
	 * 获取数组下标
	 * 
	 * @param key
	 *            数组名称
	 * @return 获取到数组下标，返回下载值，否则返回-1
	 */
	public static int getKeySub(String key) {
		List<Integer> sub = getParenthesisSub(key);
		switch (sub.size()) {
		case 0:
			return -1;
		case 2:
			if(sub.get(0) + 1 ==sub.get(1)){
				System.out.println("字符串 \""+key+"\" 格式错误");
				throw new MyException();
			}
			return Integer.valueOf(key.substring(sub.get(0) + 1, sub.get(1)));
		default:
			System.out.println("字符串 \""+key+"\" 格式错误");
			throw new MyException();
		}
//		int left = key.indexOf(ParamConst.LEFT_PARENTHESIS);
//		int right = key.indexOf(ParamConst.RIGHT_PARENTHESIS);
//		if (left < 0 || right < 0) {
//			return -1;
//		}
//		return Integer.valueOf(key.substring(left + 1, right));
	}

	/**
	 * 获取左右括号[]
	 * 
	 * @param key
	 * @return
	 */
	public static List<Integer> getParenthesisSub(String key) {
		List<Integer> sub = new ArrayList<>();
		int left = key.indexOf(ParamConst.LEFT_PARENTHESIS);
		if (left >= 0) {
			sub.add(left);
		}
		int right = key.indexOf(ParamConst.RIGHT_PARENTHESIS);
		if (right >= 0) {
			sub.add(right);
		}
		return sub;
	}

	/**
	 * 判断是否为JSON数组
	 * 
	 * @param clazz
	 *            class名称完全路径
	 * @return
	 */
	public static boolean isArray(String clazz) {
		String[] list = clazz.split(ParamConst.SEPARATOR_DOT);
		return "JSONArray".equals(list[list.length - 1]);
	}

}