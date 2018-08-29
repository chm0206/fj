package ac.cn.chm.fj.uc.controller.menu;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ac.cn.chm.fj.result.ResultInfo;
import ac.cn.chm.fj.uc.controller.base.BaseController;
import ac.cn.chm.fj.uc.service.menu.MenuInfoService;
import ac.cn.chm.fj.util.CheckUtil;
import ac.cn.chm.fj.util.init.Page;
import ac.cn.chm.fj.util.init.PageData;

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
		//JSONObject json = new JSONObject();
		ResultInfo result = new ResultInfo();
		PageData pd = this.getPageData();
		List<PageData> menuList = this.menuInfoService.listPagePd(page);
		result.put("pd",pd);
		result.put("menuList", menuList);
		return result;//ResponseUtil.returnJson(json);
	}
	@RequestMapping(value = "/toMenuInfo", produces = "application/json;charset=UTF-8")
	public Object toMenuInfo() throws Exception{
		//JSONObject json = new JSONObject();
		ResultInfo result = new ResultInfo();
		PageData pd = this.getPageData();
		String menuID = pd.getString("menuID");
		if(CheckUtil.isEmpty(menuID)){
			menuID="1";
		}
		PageData menuInfo = this.menuInfoService.findPdById(menuID);
		result.put("pd",pd);
		result.put("menuInfo", menuInfo);
		return result;//ResponseUtil.returnJson(json);
	}
	@RequestMapping(value = "/saveMenuInfo", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public Object saveUserInfo()throws Exception{
		//JSONObject json = new JSONObject();
		ResultInfo result = new ResultInfo();
		PageData pd = this.getPageData();
		result.put("menu", this.menuInfoService.findPdById(pd.getString("menuID")));
		return result;//ResponseUtil.returnJson(json);
	}
}
