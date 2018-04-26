package com.chm.fj.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chm.fj.controller.base.BaseController;
import com.chm.fj.entity.UserInfo;
import com.chm.fj.util.PageData;

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
	

	/*@Resource(name = "btnService")
	private ButtonService buttonService;*/
	
	@RequestMapping(value = "/toUserList")
	public ModelAndView toUserList() throws Exception{
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		mv.addObject("pd",pd);
		
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
