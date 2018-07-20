package com.chm.fj.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chm.fj.consts.ParamConst;
import com.chm.fj.exception.MyException;
import com.chm.fj.other.DateUtil;

public class JSONUtil {

	public static void main(String[] args) {
		String json = "{\"indexs\":[{\"id\":\"1234\",\"name\":\"chm\",\"cars\":[{\"name\":\"benz\",\"speed\":\"200km/h\"},{\"name\":\"bmw\",\"speed\":\"250km/h\"}]},{\"id\":\"567\",\"name\":\"fj\",\"cars\":[{\"name\":\"法拉利\",\"speed\":\"300km/h\"},{\"name\":\"F1\",\"speed\":\"500km/h\"}]}]}";
//		JSONObject object = JSONObject.fromObject(json);
		JSONObject object = JSONObject.parseObject(json);
		System.out.println(object);
		System.out.println(DateUtil.getNow());
		System.out.println(Tools.getJSON(object, "indexs[0].cars[1].name"));
		System.out.println(DateUtil.getNow());

	}

}