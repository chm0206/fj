package com.chm.fj.controller.group;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.chm.fj.controller.base.BaseController;
import com.chm.fj.service.group.GroupInfoService;
import com.chm.fj.util.CheckUtil;
import com.chm.fj.util.ResponseUtil;
import com.chm.fj.util.init.Page;
import com.chm.fj.util.init.PageData;

/**
 * 用户组信息
 * @author 陈焕明
 * @data 2018/05/09 23:01
 *
 */
//@Controller
@RestController//rest接口必备
@RequestMapping(value = "group")
public class GroupController extends BaseController {
	

	@Resource(name = "groupInfoService")
	private GroupInfoService groupInfoService;
	
	@RequestMapping(value = "/toGroupList", produces = "application/json;charset=UTF-8" )
	public Object toGroupList(Page page) throws Exception{
		JSONObject json = new JSONObject();
		PageData pd = this.getPageData();
		List<PageData> groupList = this.groupInfoService.listPagePd(page);
		json.put("pd", pd);
		json.put("groupList", groupList);
		return ResponseUtil.returnJson(json);
	}
	@RequestMapping(value = "/toGroupInfo", produces = "application/json;charset=UTF-8" )
	public Object toGroupInfo() throws Exception{
		JSONObject json = new JSONObject();
		PageData pd = this.getPageData();
		String gID = pd.getString("gID");
		if(CheckUtil.isEmpty(gID)){
			gID="1";
		}
		PageData groupInfo = this.groupInfoService.findPdById(gID);
		json.put("pd",pd);
		json.put("groupInfo", groupInfo);
		return ResponseUtil.returnJson(json);//不使用toString
	}
	@RequestMapping(value = "/saveGroupInfo", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public Object saveGroupInfo()throws Exception{
		JSONObject json = new JSONObject();
		PageData pd = this.getPageData();
		json.put("group", this.groupInfoService.findPdById(pd.getString("gID")));
		return ResponseUtil.returnJson(json);
	}
}
