package ac.cn.chm.fj.uc.controller.sso;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ac.cn.chm.fj.consts.CodeConst;
import ac.cn.chm.fj.consts.ParamConst;
import ac.cn.chm.fj.consts.StringConst;
import ac.cn.chm.fj.consts.UrlConst;
import ac.cn.chm.fj.result.ResultInfo;
import ac.cn.chm.fj.uc.controller.base.BaseController;
import ac.cn.chm.fj.uc.redis.JedisCacheClient;
import ac.cn.chm.fj.uc.service.user.UserInfoService;
import ac.cn.chm.fj.uc.util.RedisUtil;
import ac.cn.chm.fj.util.CheckUtil;
import ac.cn.chm.fj.util.Tools;
import ac.cn.chm.fj.util.init.PageData;

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
			return new ResultInfo(CodeConst.USER_OR_PASS_ERROR, "用户账号或密码错误");
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
			pd.put(StringConst.REDIS_USER_INFO, userInfo);
			RedisUtil.userLogin(jedis,pd);
			result.put(StringConst.REDIS_ACC_TOKEN,accToken);
			if(ParamConst.GET_USERINFO.equals(pd.get(ParamConst.GET_INFO))){
				result.put(StringConst.REDIS_USER_INFO,userInfo);
			}
			result.put(StringConst.REDIRECT_URL, UrlConst.PAGE_INDEX);// 登录成功跳转到登录页面
		}
		return result;
	}
}
