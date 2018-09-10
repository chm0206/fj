package ac.cn.chm.fj.mall.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import ac.cn.chm.fj.api.user.LoginApi;
import ac.cn.chm.fj.api.util.ApiConst;
import ac.cn.chm.fj.consts.StringConst;
import ac.cn.chm.fj.consts.UrlConst;
import ac.cn.chm.fj.mall.controller.base.BaseController;
import ac.cn.chm.fj.util.CheckUtil;
import ac.cn.chm.fj.util.Tools;
import ac.cn.chm.fj.util.init.PageData;

public class LoginInterceptor extends BaseController implements HandlerInterceptor {

	// @Autowired
	// private JedisCacheClient jedis;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		PageData pd = this.getPageData();
		// 判断是否有accToken
		String accToken = CheckUtil.isEmpty(pd.getString(StringConst.REDIS_ACC_TOKEN))
				? Tools.getCkValue(request, StringConst.REDIS_ACC_TOKEN) : pd.getString(StringConst.REDIS_ACC_TOKEN);
		if (CheckUtil.isEmpty(accToken)) {
			toLogin(request,response);
			return false;
		} else {
			pd.put("accToken", accToken);
		}
		// accToken是否已过期
		boolean unlisted = new LoginApi("accessKey", "accessPass").isLogin(pd);
		if (unlisted) {
			toLogin(request,response);
			return false;
		}
		/*
		 * String userID = jedis.getVStr(accToken, 0); if
		 * (CheckUtil.isEmpty(userID)) { System.out.println("登录过期，调到登录页面");
		 * response.sendRedirect(request.getContextPath() + "/" +
		 * UrlConst.PAGE_LOGIN); return false; }
		 */
		System.out.println("已登录");
		return true;
	}
	/**
	 * 跳转到SSO登录页面
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void toLogin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		System.out.println("登录过期，跳转到单点登录页面");
		//System.out.println(request.getRequestURI());//只获取相对路径
		String value = request.getQueryString();	//获取参数
		response.sendRedirect(ApiConst.UC_SSO_LOGIN+"?p="+request.getRequestURL()+(CheckUtil.isEmpty(value)?"":"?"+value));//加上当前目录//request.getContextPath() + "/" + 
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