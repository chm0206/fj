package ac.cn.chm.fj.mall.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import ac.cn.chm.fj.api.user.LoginApi;
import ac.cn.chm.fj.api.util.ApiConst;
import ac.cn.chm.fj.consts.StringConst;
import ac.cn.chm.fj.mall.controller.base.BaseController;
import ac.cn.chm.fj.util.CheckUtil;
import ac.cn.chm.fj.util.Tools;
import ac.cn.chm.fj.util.init.PageData;

public class LoginInterceptor extends BaseController implements HandlerInterceptor {

	// @Autowired
	// private JedisCacheClient jedis;
	public static final String accessKey = "accessKey";
	public static final String accessPass = "accessPass";
	/**
	 * 校验用户是否已登录
	 */
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
		boolean unlisted = new LoginApi(accessKey, accessPass).isLogin(pd);//调用该方法，若用户仍在线，会更新redis的在线时间
		if (unlisted) {
			toLogin(request,response);
			return false;
		}else{
			HttpSession session = request.getSession();
			JSONObject userInfo = (JSONObject) session.getAttribute("userInfo");
			if(userInfo==null){
				//已登录，但未保存基础用户信息
				//获取用户基础信息
				userInfo = new LoginApi(accessKey, accessPass).getLoginInfo(accToken);
				session.setAttribute("userInfo",userInfo);
			}
		}
		//System.out.println("已登录");
		return true;
	}
	/**
	 * 跳转到SSO登录页面
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void toLogin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//System.out.println("登录过期，跳转到单点登录页面");
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