package com.chm.fj.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JSONUtil {

	public static void main(String[] args) {
		String json = "{\"indexs\":[{\"id\":\"1234\",\"name\":\"chm\",\"cars\":[{\"name\":\"benz\",\"speed\":\"200km/h\"},{\"name\":\"bmw\",\"speed\":\"250km/h\"}]},{\"id\":\"567\",\"name\":\"fj\",\"cars\":[{\"name\":\"法拉利\",\"speed\":\"300km/h\"},{\"name\":\"F1\",\"speed\":\"500km/h\"}]}]}";
		String list = "[{\"id\":\"1234\",\"name\":\"chm\",\"cars\":[{\"name\":\"benz\",\"speed\":\"200km/h\"},{\"name\":\"bmw\",\"speed\":\"250km/h\"}]},{\"id\":\"567\",\"name\":\"fj\",\"cars\":[{\"name\":\"法拉利\",\"speed\":\"300km/h\"},{\"name\":\"F1\",\"speed\":\"500km/h\"}]}]";
//		JSONObject object = JSONObject.fromObject(json);
		JSONObject object = JSONObject.parseObject(json);
		JSONArray array = JSONArray.parseArray(list);
		System.out.println(Tools.getJSON(array,"array[0].cars[4].name"));
		//System.out.println(checkJson("indexs[0].cars1[1].name"));
		//System.out.println(object);
		//System.out.println(DateUtil.getNow());
		System.out.println(Tools.getJSON(object, "indexs[0].cars[1].name"));
		//System.out.println(DateUtil.getNow());
	}
	
	public static boolean checkJson(String str){
		//是否有[]，中间是否有字符
		//不为点开始，结束
		//[]在结束位置
		String regex = "^([a-zA-Z]\\w*(\\[\\d+\\])?\\.)+([a-zA-Z]\\w*(\\[\\d+\\])?)$";
		return str.matches(regex);

	}
}