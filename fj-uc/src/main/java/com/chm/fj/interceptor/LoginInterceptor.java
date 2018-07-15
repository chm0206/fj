package com.chm.fj.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chm.fj.consts.StringConst;
import com.chm.fj.consts.UrlConst;
import com.chm.fj.controller.base.BaseController;
import com.chm.fj.redis.JedisCacheClient;
import com.chm.fj.util.CheckUtil;
import com.chm.fj.util.CookieUtil;
import com.chm.fj.util.init.PageData;

public class LoginInterceptor extends BaseController implements HandlerInterceptor {

	@Autowired
	private JedisCacheClient jedis;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		PageData pd = this.getPageData();
		// 判断是否有accToken
		String accToken = CheckUtil.isEmpty(pd.getString(StringConst.REDIS_ACCTOKEN))
				? CookieUtil.getCkValue(request, StringConst.REDIS_ACCTOKEN):pd.getString(StringConst.REDIS_ACCTOKEN);
		if (CheckUtil.isEmpty(accToken)) {
			System.out.println("尚未登录，调到登录页面");
			response.sendRedirect(request.getContextPath() + "/" + UrlConst.PAGE_LOGIN);
			return false;
		}
		// accToken是否已过期
		String userID = jedis.getVStr(accToken, 0);
		if (CheckUtil.isEmpty(userID)) {
			System.out.println("登录过期，调到登录页面");
			response.sendRedirect(request.getContextPath() + "/" + UrlConst.PAGE_LOGIN);
			return false;
		}
		System.out.println("已登录");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion");
	}

}