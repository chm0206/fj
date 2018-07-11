package com.chm.fj.controller.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chm.fj.controller.base.BaseController;
import com.chm.fj.entity.UserInfo;
import com.chm.fj.service.user.UserInfoService;
import com.chm.fj.util.Tools;
import com.chm.fj.util.init.Page;
import com.chm.fj.util.init.PageData;

import net.sf.json.JSONObject;

/**
 * 用户信息
 * @author Administrator
 * @data 2018/04/26 23:01
 *
 */
@Controller
@RequestMapping(value = "user")
public class UserController extends BaseController {
	

	@Resource(name = "userInfoService")
	private UserInfoService userInfoService;
	
	@RequestMapping(value = "/toUserList")
	public ModelAndView toUserList(Page page) throws Exception{
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		List<PageData> userList = this.userInfoService.listPagePd(page);
		mv.addObject("pd",pd);
		mv.setViewName("user/userList");
		return mv;
	}
	@RequestMapping(value = "/toUserInfo")
	public ModelAndView toUserInfo() throws Exception{
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userID = pd.getString("userID");
		if(Tools.isEmpty(userID)){
			userID="1";
		}
		PageData userInfo = this.userInfoService.findPdById(userID);
		mv.addObject("pd",pd);
		mv.setViewName("user/userList");
		return mv;
	}
	@RequestMapping(value = "/saveUserInfo", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public Object saveUserInfo()throws Exception{
		JSONObject object = new JSONObject();
		PageData pd = this.getPageData();
		object.put("user", new UserInfo());
		return object.toString();
	}
}
