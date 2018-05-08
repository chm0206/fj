package com.chm.fj.controller.group;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chm.fj.controller.base.BaseController;
import com.chm.fj.entity.UserInfo;
import com.chm.fj.service.group.GroupInfoService;
import com.chm.fj.util.Page;
import com.chm.fj.util.PageData;
import com.chm.fj.util.Tools;

import net.sf.json.JSONObject;

/**
 * 用户组信息
 * @author 陈焕明
 * @data 2018/05/09 23:01
 *
 */
@Controller
@RequestMapping(value = "group")
public class GroupController extends BaseController {
	

	@Resource(name = "groupInfoService")
	private GroupInfoService groupInfoService;
	
	@RequestMapping(value = "/toGroupList")
	public ModelAndView toUserList(Page page) throws Exception{
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		List<PageData> userList = this.groupInfoService.listPagePd(page);
		mv.addObject("pd",pd);
		mv.setViewName("user/userList");
		return mv;
	}
	@RequestMapping(value = "/toGroupInfo")
	public ModelAndView toUserInfo() throws Exception{
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String gID = pd.getString("gID");
		if(Tools.isEmpty(gID)){
			gID="1";
		}
		PageData userInfo = this.groupInfoService.findPdById(gID);
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
