package com.chm.fj.util;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSONObject;
import com.chm.fj.consts.ParamConst;

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
	 * 使用默认分隔符","进行字符串分割
	 * @param str
	 * @return
	 */
	public static String[] str2Array(String str){
		return str2Array(str,ParamConst.DIVISION_KOMMA);
	}
	/**
	 * 使用指定的字符串"split"对字符串"str"进行分割
	 * @param str
	 * @param split
	 * @return
	 */
	public static String[] str2Array(String str,String split){
		if(CheckUtil.isEmpty(str)){
			return null;
		}
		return str.split(split);
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
	
	/**
	 * Map to Bean
	 * @param map
	 * @param class1
	 * @author chm
	 * @return
	 */
	public static <T> T map2Bean(Map<String, String> map, Class<T> class1) {  
        T bean = null;  
        try {  
            bean = class1.newInstance();  
            BeanUtils.populate(bean, map);  
        } catch (InstantiationException e) {  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        } catch (InvocationTargetException e) {  
            e.printStackTrace();  
        }  
        return bean;  
    } 
	
	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date){
		if(CheckUtil.notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date,String format){
		if(CheckUtil.notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}
}
