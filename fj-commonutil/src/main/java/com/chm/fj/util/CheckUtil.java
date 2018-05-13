package com.chm.fj.util;

import java.util.List;

public class CheckUtil {
	/**
	 * 判断对象是否为空
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Object object){
		return true;
	}
	/**
	 * 判断对象是否非空
	 * @param object
	 * @return
	 */
	public static boolean notEmpty(Object object){
		return true;
	}
	
	/**
	 * 判断对象是否为空
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(List<?> obj){
		return obj == null || obj.size()<=0;
	}
	/**
	 * 判断对象是否非空
	 * @param object
	 * @return
	 */
	public static boolean notEmpty(List<?> obj){
		return !isEmpty(obj) ;
	}
	
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}
	
	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}
}
