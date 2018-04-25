package com.chm.fj.consts;

public class ParamConst {
	/*** 每页默认记录数 20 */
	public static final String PAGE_SIZE ="20";
	/**参数间分隔符(尽量别改)*/
	public static final String PARAM_REGEX = "\\|";
	/**参数内分隔符(尽量别改)*/
	public static final String VALUE_REGEX = "\\#";
	/*** TOKEN_KEY */
	public static final String TOKEN_KEY = "huazhou" ;
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
	
}
