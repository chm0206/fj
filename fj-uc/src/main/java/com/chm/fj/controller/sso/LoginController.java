package com.chm.fj.controller.sso;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chm.fj.Result.ResultInfo;
import com.chm.fj.consts.ParamConst;
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
import com.chm.fj.util.CheckUtil;
import com.chm.fj.util.RedisUtil;
import com.chm.fj.util.Tools;
import com.chm.fj.util.init.PageData;

@Controller
@RequestMapping(value = "login")
public class LoginController extends BaseController {

	@Resource(name = "userInfoService")
	private UserInfoService userInfoService;

	@RequestMapping(value = "toLogin")
	public ModelAndView toLogin() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/login");
		mv.addObject("loginUrl", UrlConst.ACTION_LOGIN);
		mv.addObject("toRegister", UrlConst.PAGE_REGISTER);
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
			if(CheckUtil.isEmpty(pd.getString(StringConst.SSO_TERMAINAL))){//判断终端是否为空
				pd.put(StringConst.SSO_TERMAINAL, StringConst.SSO_TERMAINAL_COMPUTER);//默认为电脑
			}
			//String accTokenValue = userInfo.getString(StringConst.USER_ID) + "," + DateUtil.getTimeStamp();// 保存usrID和时间戳
			//String accTokenKey = userInfo.getString(StringConst.USER_ID);
			//
			/**
			 * 序号	说明			key				value
			 * 1	accToken	时间戳转换码		userID,ownerID
			 * 2	用户信息		userInfo		用户信息
			 * 3	登录终端码	userInfo+term	accToken+推送地址
			 */
			//accToken-key、value
			String accToken = Tools.greantAccToken();
			pd.put(StringConst.REDIS_ACC_TOKEN, accToken);
			RedisUtil.userLogin(pd);
			result.put(StringConst.REDIS_ACC_TOKEN,accToken);
			if(ParamConst.GET_USERINFO.equals(pd.get(ParamConst.GET_INFO))){
				result.put(StringConst.REDIRECT_URL, UrlConst.PAGE_INDEX);// 登录成功跳转到登录页面
			}
		}
		return result;
	}
}
