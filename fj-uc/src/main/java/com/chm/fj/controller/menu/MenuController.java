package com.chm.fj.controller.menu;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chm.fj.controller.base.BaseController;
import com.chm.fj.service.menu.MenuInfoService;
import com.chm.fj.util.CheckUtil;
import com.chm.fj.util.ResponseUtil;
import com.chm.fj.util.init.Page;
import com.chm.fj.util.init.PageData;

import net.sf.json.JSONObject;

/**
 * 菜单信息
 * @author 陈焕明
 * @data 2018/05/09 23:01
 *
 */
@RestController
@RequestMapping(value = "menu")
public class MenuController extends BaseController {
	

	@Resource(name = "menuInfoService")
	private MenuInfoService menuInfoService;
	
	@RequestMapping(value = "/toMenuList", produces = "application/json;charset=UTF-8")
	public Object toUserList(Page page) throws Exception{
		JSONObject json = new JSONObject();
		PageData pd = this.getPageData();
		List<PageData> menuList = this.menuInfoService.listPagePd(page);
		json.put("pd",pd);
		json.put("menuList", menuList);
		return ResponseUtil.returnJson(json);
	}
	@RequestMapping(value = "/toMenuInfo", produces = "application/json;charset=UTF-8")
	public Object toMenuInfo() throws Exception{
		JSONObject json = new JSONObject();
		PageData pd = this.getPageData();
		String menuID = pd.getString("menuID");
		if(CheckUtil.isEmpty(menuID)){
			menuID="1";
		}
		PageData menuInfo = this.menuInfoService.findPdById(menuID);
		json.put("pd",pd);
		json.put("menuInfo", menuInfo);
		return ResponseUtil.returnJson(json);
	}
	@RequestMapping(value = "/saveMenuInfo", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public Object saveUserInfo()throws Exception{
		JSONObject json = new JSONObject();
		PageData pd = this.getPageData();
		json.put("menu", this.menuInfoService.findPdById(pd.getString("menuID")));
		return ResponseUtil.returnJson(json);
	}
}
