package com.chm.fj.controller.main;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chm.fj.controller.base.BaseController;
import com.chm.fj.service.menu.MenuInfoService;
import com.chm.fj.util.PageData;
/**
 * 主入口，首页，登录，找回密码等不需要拦截的页面都在这
 * @author Administrator
 *
 */
@Controller
public class MainController extends BaseController {


	@Resource(name = "menuInfoService")
	private MenuInfoService menuInfoService;
	
	@RequestMapping(value="/toIndex")
	public ModelAndView toIndex() throws Exception{
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		//获取菜单信息
		List<PageData> menuList = this.menuInfoService.listAllMenu("0");
		mv.addObject("menuList",menuList);
		mv.addObject("pd",pd);
		mv.setViewName("main/main");
		return mv;
	}
}
