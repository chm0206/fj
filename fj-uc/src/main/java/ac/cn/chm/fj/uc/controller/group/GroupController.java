package ac.cn.chm.fj.uc.controller.group;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ac.cn.chm.fj.result.ResultInfo;
import ac.cn.chm.fj.uc.controller.base.BaseController;
import ac.cn.chm.fj.uc.service.group.GroupInfoService;
import ac.cn.chm.fj.util.CheckUtil;
import ac.cn.chm.fj.util.init.Page;
import ac.cn.chm.fj.util.init.PageData;

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
		//JSONObject json = new JSONObject();
		ResultInfo result = new ResultInfo();
		PageData pd = this.getPageData();
		List<PageData> groupList = this.groupInfoService.listPagePd(page);
		result.put("pd", pd);
		result.put("groupList", groupList);
		return result;//ResponseUtil.returnJson(json);
	}
	@RequestMapping(value = "/toGroupInfo", produces = "application/json;charset=UTF-8" )
	public Object toGroupInfo() throws Exception{
		//JSONObject json = new JSONObject();
		ResultInfo result = new ResultInfo();
		PageData pd = this.getPageData();
		String gID = pd.getString("gID");
		if(CheckUtil.isEmpty(gID)){
			gID="1";
		}
		PageData groupInfo = this.groupInfoService.findPdById(gID);
		result.put("pd",pd);
		result.put("groupInfo", groupInfo);
		return result;//ResponseUtil.returnJson(json);//不使用toString
	}
	@RequestMapping(value = "/saveGroupInfo", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public Object saveGroupInfo()throws Exception{
		//JSONObject json = new JSONObject();
		ResultInfo result = new ResultInfo();
		PageData pd = this.getPageData();
		result.put("group", this.groupInfoService.findPdById(pd.getString("gID")));
		return result;//ResponseUtil.returnJson(json);
	}
}
