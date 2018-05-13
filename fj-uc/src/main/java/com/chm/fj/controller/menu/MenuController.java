package com.chm.fj.controller.menu;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chm.fj.controller.base.BaseController;
import com.chm.fj.entity.UserInfo;
import com.chm.fj.service.menu.MenuInfoService;
import com.chm.fj.util.CheckUtil;
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
@RequestMapping(value = "menu")
public class MenuController extends BaseController {
	

	@Resource(name = "menuInfoService")
	private MenuInfoService menuInfoService;
	
	@RequestMapping(value = "/toMenuList")
	public ModelAndView toUserList(Page page) throws Exception{
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		List<PageData> userList = this.menuInfoService.listPagePd(page);
		mv.addObject("pd",pd);
		mv.setViewName("user/userList");
		return mv;
	}
	@RequestMapping(value = "/toMenuInfo")
	public ModelAndView toUserInfo() throws Exception{
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String menuID = pd.getString("menuID");
		if(CheckUtil.isEmpty(menuID)){
			menuID="1";
		}
		PageData userInfo = this.menuInfoService.findPdById(menuID);
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
