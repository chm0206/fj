package ac.cn.chm.fj.api.util;

public class ApiConst {
	/**
	 * 用户中心基准路径
	 */
	public static final String UC_URL = "http://chm.cn:8080/uc/";
	/**
	 * 电商系统基准路径
	 */
	public static final String MALL_URL = "http://chm.cn:8081/mall/";
	/**
	 * 单点登录校验地址
	 */
	public static final String UC_CHECK_LOGIN = UC_URL + "sso/isLogin";
	/**
	 * 获取用户登录基准信息
	 */
	public static final String UC_LOGIN_INFO = UC_URL+"sso/getLoginInfo";
	/**
	 * 单点登录页面
	 */
	public static final String UC_SSO_LOGIN = UC_URL+"sso/toLogin";
}
