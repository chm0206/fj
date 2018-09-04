package ac.cn.chm.fj.uc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import ac.cn.chm.fj.consts.StringConst;
import ac.cn.chm.fj.consts.UrlConst;
import ac.cn.chm.fj.uc.controller.base.BaseController;
import ac.cn.chm.fj.uc.redis.JedisCacheClient;
import ac.cn.chm.fj.util.CheckUtil;
import ac.cn.chm.fj.util.Tools;
import ac.cn.chm.fj.util.init.PageData;

public class LoginInterceptor extends BaseController implements HandlerInterceptor {

	@Autowired
	private JedisCacheClient jedis;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		PageData pd = this.getPageData();
		// 判断是否有accToken
		String accToken = CheckUtil.isEmpty(pd.getString(StringConst.REDIS_ACC_TOKEN))
				? Tools.getCkValue(request, StringConst.REDIS_ACC_TOKEN):pd.getString(StringConst.REDIS_ACC_TOKEN);
		if (CheckUtil.isEmpty(accToken)) {
			System.out.println("尚未登录，调到登录页面");
			System.out.println(request.getContextPath() + "/" + UrlConst.PAGE_LOGIN);
			response.sendRedirect(request.getContextPath() + "/" + UrlConst.PAGE_LOGIN);
			return false;
		}
		// accToken是否已过期
		boolean unlisted = jedis.notExpire(accToken, 0);
		if(unlisted){
			System.out.println("登录过期，调到登录页面");
			response.sendRedirect(request.getContextPath() + "/" + UrlConst.PAGE_LOGIN);
			return false;
		}
		/*String userID = jedis.getVStr(accToken, 0);
		if (CheckUtil.isEmpty(userID)) {
			System.out.println("登录过期，调到登录页面");
			response.sendRedirect(request.getContextPath() + "/" + UrlConst.PAGE_LOGIN);
			return false;
		}*/
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