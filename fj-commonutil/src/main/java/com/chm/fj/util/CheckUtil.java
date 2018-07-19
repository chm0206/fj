package com.chm.fj.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	/**
	  * 验证邮箱
	  * @param email
	  * @return
	  */
	 public static boolean checkEmail(String email){
	  boolean flag = false;
	  try{
	    String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	    Pattern regex = Pattern.compile(check);
	    Matcher matcher = regex.matcher(email);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	
	 /**
	  * 验证手机号码
	  * @param mobiles
	  * @return
	  */
	 public static boolean checkMobileNumber(String mobileNumber){
	  boolean flag = false;
	  try{
	    Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
	    Matcher matcher = regex.matcher(mobileNumber);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
}
