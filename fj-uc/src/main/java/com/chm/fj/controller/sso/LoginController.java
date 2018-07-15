package com.chm.fj.controller.sso;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chm.fj.Result.ResultInfo;
import com.chm.fj.consts.RedisConst;
import com.chm.fj.consts.StatusCodeConst;
import com.chm.fj.consts.StringConst;
import com.chm.fj.consts.UrlConst;
import com.chm.fj.controller.base.BaseController;
import com.chm.fj.other.DateUtil;
import com.chm.fj.redis.JedisCacheClient;
/**
 * 登录控制器，后续会改为单点登录
 * @author chm
 * @data 2018/04/46 23:34
 */
import com.chm.fj.service.user.UserInfoService;
import com.chm.fj.util.init.PageData;

@Controller
@RequestMapping(value = "login")
public class LoginController extends BaseController {

	@Resource(name = "userInfoService")
	private UserInfoService userInfoService;

	@Autowired
	private JedisCacheClient jedis;

	@RequestMapping(value = "toLogin")
	public ModelAndView toLogin() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/login");
		mv.addObject("loginUrl",UrlConst.ACTION_LOGIN);
		mv.addObject("toRegister",UrlConst.PAGE_REGISTER);
		return mv;
	}

	@RequestMapping(value = "index")
	public ModelAndView toIndex() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/index");
		return mv;
	}

	@RequestMapping(value = "/login")
	@ResponseBody
	public ResultInfo login() throws Exception {
		ResultInfo result = new ResultInfo();
		PageData pd = this.getPageData();
		PageData userInfo = this.userInfoService.findUserLoginInfo(pd);
		if (userInfo == null) {// 找不到此用户或用户密码错误
			return new ResultInfo(StatusCodeConst.USER_OR_PASS_ERROR, "用户账号或密码错误");
		} else {// 登录成功,保存用户信息、生成accToken
			String accTokenValue = userInfo.getString(StringConst.USER_ID) + "," + DateUtil.getTimeStamp();// 保存usrID和时间戳
			String accTokenKey = userInfo.getString(StringConst.USER_ID);
			jedis.setVExpire(accTokenKey, accTokenValue, RedisConst.EXPIRE_30_MINUTE, 0);
			String userInfoKey = StringConst.REDIS_USER_INFO + userInfo.getString(StringConst.USER_ID);
			jedis.setVExpire(userInfoKey, userInfo, RedisConst.EXPIRE_30_MINUTE, 0);
			result.put(StringConst.REDIS_ACCTOKEN, accTokenKey);	//保存accToken的键值
			result.put(StringConst.REDIS_USER_INFO,userInfoKey);	//返回用户信息的键值
			result.put(StringConst.REDIRECT_URL, UrlConst.PAGE_INDEX);// 登录成功跳转到登录页面
		}
		return result;
	}
}
