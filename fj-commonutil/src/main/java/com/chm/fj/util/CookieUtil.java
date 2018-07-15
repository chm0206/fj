package com.chm.fj.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
	public static Cookie[] getCookies(HttpServletRequest request){
		Cookie[] cookies = (Cookie[]) request.getCookies();
		return cookies;
	}
	
	public static String getCkValue(HttpServletRequest request,String key){
		if(CheckUtil.isEmpty(key)){
			return null;
		}
		Cookie[] cookies = (Cookie[]) request.getCookies();
		if(cookies == null || cookies.length<=0){
			return null;
		}
		for (Cookie cookie : cookies) {
			if(key.equals(cookie.getName())){
				return cookie.getValue();
			}
		}
		return null;
	}
}
