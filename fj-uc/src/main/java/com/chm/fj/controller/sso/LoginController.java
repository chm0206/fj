package com.chm.fj.controller.sso;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chm.fj.controller.base.BaseController;
/**
 * 登录控制器，后续会改为单点登录
 * @author chm
 * @data 2018/04/46 23:34
 */
import com.chm.fj.service.user.UserInfoService;
import com.chm.fj.util.init.PageData;
import com.sun.tools.internal.ws.processor.model.ModelObject;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value="login")
public class LoginController extends BaseController {
	@Resource(name = "userInfoService")
	private UserInfoService userInfoService;
	public Object toLogin() throws Exception{
		JSONObject object = new JSONObject();
		PageData pd = new PageData();
		
		return object;
	}
}
