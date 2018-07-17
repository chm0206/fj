package com.chm.fj.consts;

public class ParamConst {
	/*** 每页默认记录数 20 */
	public static final String PAGE_SIZE ="20";
	/**参数间分隔符(尽量别改)*/
	public static final String DIVISION_PARAM_REGEX = "\\|";
	/**参数内分隔符(尽量别改)*/
	public static final String DIVISION_VALUE_REGEX = "\\#";
	/**
	 * 分割符","
	 */
	public static final String DIVISION_KOMMA = ",";
	/*** TOKEN_KEY */
	public static final String TOKEN_KEY = "FJ" ;
	/**
	 * TOKEN_KEY_LEN
	 * 长度 = 40 - n （16 = 40 - 24）
	 */
	public static final int TOKEN_KEY_LEN = 24 ;
	/*** 加密方式 SHA-1 */
	public static final String SHA = "SHA-1";
	

	/***param userID 字段****/
	public static final String FJ_USER_ID = "userID" ;
	
	public static final String FJ_ACC_TOKEN = "accToken";
	/**
	 * 包含子菜单
	 */
	public static final Boolean MENU_IS_CHILD = true;
	/**
	 * 不包含子菜单
	 */
	public static final Boolean MENU_NOT_CHILD = false;
	
	public static final Integer GET_USERINFO = 1;
	public static final String GET_INFO = "getInfo";
	/**
	 * 根菜单
	 */
	public static final String MENU_ROOT_ID = "0";
	/**
	 * 菜单正常状态
	 */
	public static final String MENU_STATUS_NORMAL = "0";
	/**
	 * 菜单锁定状态
	 */
	public static final String MENU_STATUS_LOCKING="L";
	
	/**
	 * 左括号
	 */
	public static final String LEFT_PARENTHESIS = "[";
	/**
	 * 右括号
	 */
	public static final String RIGHT_PARENTHESIS = "]";
	/**
	 * 点
	 */
	public static final String SEPARATOR_DOT = "\\.";
	
}
