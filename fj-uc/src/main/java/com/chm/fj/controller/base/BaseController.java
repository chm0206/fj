package com.chm.fj.controller.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.chm.fj.util.FormatUtil;
import com.chm.fj.util.init.Jurisdiction;
import com.chm.fj.util.init.Page;
import com.chm.fj.util.init.PageData;

/**
 * @author riseinfo.cn 修改时间：2015、12、11
 */
public class BaseController {

	protected Logger logger = Logger.getLogger(this.getClass());

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 6357869213649815390L;
	/**
	 * new PageData对象
	 * 
	 * @return
	 */
	public PageData getPageData() {
		return new PageData(this.getRequest());
	}

	/**
	 * 得到ModelAndView
	 * 
	 * @return
	 */
	public ModelAndView getModelAndView() {
		return new ModelAndView();
	}

	/**
	 * 得到request对象
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}

	/**
	 * 得到32位的uuid
	 * 
	 * @return
	 */
	public String get32UUID() {
		return UUID.randomUUID().toString().trim().replaceAll("-", "");
	}

	/**
	 * 得到13位的uuid
	 * 
	 * @return
	 */
	public String get13UUID() {
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		sdfTime.format(new Date());
		return Long.toString(FormatUtil.str2Date(sdfTime.format(new Date())).getTime()) ;
		//return Long.toString(System.currentTimeMillis());
	}

	/**
	 * 获取session内容
	 */
	public HttpSession getLocalSession() {
		HttpSession session = null ;
		try {
			 session = Jurisdiction.getSession();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return session ;
	}

	/**
	 * 获取oOwner
	 */
	public String getOwner() {
		return null;
		// return ((UserInfo)
		// this.getLocalSession().getAttribute(ParamConst.SESSION_USER)).getOperatorId();
	}

	/**
	 * 得到分页列表的信息
	 * 
	 * @return
	 */
	public Page getPage() {
		return new Page();
	}

	public static void logBefore(Logger logger, String interfaceName) {
		// logger.info("");
		// logger.info("start");
		logger.info(interfaceName);
	}

	public static void logAfter(Logger logger) {
		// logger.info("end");
		// logger.info("");
	}

	/**
	 * 获取登录用户信息
	 * 
	 * @return
	 *//*
	public UserInfo getLoginUserInfo() {
		HttpSession session = null;
		try {
			session = Jurisdiction.getSession();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserInfo user = (UserInfo) session.getAttribute(ParamConst.SESSION_USER);
		return user;
	}
*/
	/**
	 * 获取用户登录IP
	 * 
	 * @return
	 * @date 2017年4月10日
	 */
	public String getLoginIP() {
		HttpServletRequest request = this.getRequest();
		String ip = "";
		if (request.getHeader("x-forwarded-for") == null) {
			ip = request.getRemoteAddr();
		} else {
			ip = request.getHeader("x-forwarded-for");
		}
		return ip;
	}

	/**
	 * session存放
	 * 
	 * @param key
	 *            键
	 * @param obj
	 *            值
	 */
	public void setSession(String key, Object obj) {
		try {
			HttpSession session = Jurisdiction.getSession();
			session.setAttribute(key, obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取session或redis中的用户信息
	 * 
	 * @author tk
	 * @Date 2017-07-10
	 * @return 用户信息
	 *//*
	public static UserInfo getUserInfo(String userID) {
		UserInfo user = new UserInfo() ;
		if (ParamConst.isRedis) {
			if(Tools.notEmpty(userID)){
				Jedis jedis = RedisConnect.getJedis() ;
				//userId = jedis.get(ParamConst.REDIS_USERID); //????
				user = (UserInfo) SerializeUtil.unserialize(jedis.get(SerializeUtil.serialize(userID+ParamConst.REDIS_USER)));
				RedisConnect.returnResource(jedis);
			}else{
				user = null ;
			}
		} else {
			Session session = SecurityUtils.getSubject().getSession();
			user = (UserInfo) session.getAttribute(userID+ParamConst.SESSION_USER);
		}
		
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public static List<RoleUser> getUserForRole(String userID) {
		List<RoleUser> ruList = null;
		if (ParamConst.isSession) { // 获取session中的角色用户、按钮权限
			Session session = SecurityUtils.getSubject().getSession();
			ruList = (List<RoleUser>) session.getAttribute(userID + ParamConst.SESSION_ROLEUSER);
		} else { // 获取存放redis中的角色用户、按钮权限
			Jedis jedis = RedisConnect.getJedis();
			byte[] roleUserByte = jedis.get(SerializeUtil.serialize(userID + ParamConst.REDIS_ROLEUSER));
			ruList = (List<RoleUser>) SerializeUtil.unserialize(roleUserByte);
			RedisConnect.returnResource(jedis);
		}
		return ruList;
	}*/
	
	/**
	 * 去除pd对象中指定的空字符
	 * @param pd
	 * @param strName
	 */
	public void replaceBlank(PageData pd,String strName) {
		if("#".equals(pd.getString(strName))){
			pd.put(strName, "") ;
		}
	}
}
