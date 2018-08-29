package ac.cn.chm.fj.uc.controller.main;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ac.cn.chm.fj.other.DateUtil;
import ac.cn.chm.fj.result.ResultInfo;
import ac.cn.chm.fj.uc.controller.base.BaseController;
import ac.cn.chm.fj.uc.entity.MenuInfo;
import ac.cn.chm.fj.uc.service.menu.MenuInfoService;
import ac.cn.chm.fj.util.init.PageData;
/**
 * 主入口，首页，登录，找回密码等不需要拦截的页面都在这
 * @author Administrator
 *
 */
//@Controller
@RestController//rest接口必备
public class MainController extends BaseController {


	@Resource(name = "menuInfoService")
	private MenuInfoService menuInfoService;
	
	@RequestMapping(value="/toIndex")
	public Object toIndex() throws Exception{
		//JSONObject json = new JSONObject();
		ResultInfo result = new ResultInfo();
		PageData pd = this.getPageData();
		//获取菜单信息
		System.out.println(DateUtil.getNow());
		List<MenuInfo> menuList = this.menuInfoService.getAllMenu();
		System.out.println(DateUtil.getNow());
		result.put("menuList",menuList);
		result.put("pd",pd);
		return result;//ResponseUtil.returnJson(json);
	}
}
