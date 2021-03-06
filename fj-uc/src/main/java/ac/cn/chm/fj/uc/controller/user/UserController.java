package ac.cn.chm.fj.uc.controller.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ac.cn.chm.fj.result.ResultInfo;
import ac.cn.chm.fj.uc.controller.base.BaseController;
import ac.cn.chm.fj.uc.entity.UserInfo;
import ac.cn.chm.fj.uc.service.user.UserInfoService;
import ac.cn.chm.fj.util.CheckUtil;
import ac.cn.chm.fj.util.init.Page;
import ac.cn.chm.fj.util.init.PageData;

/**
 * 用户信息
 * @author Administrator
 * @data 2018/04/26 23:01
 *
 */
@Controller
@RequestMapping(value = "user")
public class UserController extends BaseController {
	

	@Resource(name = "userInfoService")
	private UserInfoService userInfoService;
	
	@RequestMapping(value = "/toUserList")
	public ModelAndView toUserList(Page page) throws Exception{
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		List<PageData> userList = this.userInfoService.listPagePd(page);
		mv.addObject("userList",userList);
		mv.addObject("pd",pd);
		mv.setViewName("user/userList");
		return mv;
	}
	@RequestMapping(value = "/toUserInfo")
	public ModelAndView toUserInfo() throws Exception{
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userID = pd.getString("userID");
		if(CheckUtil.isEmpty(userID)){
			userID="1";
		}
		PageData userInfo = this.userInfoService.findPdById(userID);
		mv.addObject("userInfo",userInfo);
		mv.addObject("pd",pd);
		mv.setViewName("user/userList");
		return mv;
	}
	@RequestMapping(value = "/saveUserInfo", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public ResultInfo saveUserInfo()throws Exception{
		ResultInfo result  = new ResultInfo();
		//JSONObject object = new JSONObject();
		PageData pd = this.getPageData();
		result.put("user", new UserInfo());
		result.put("pd",pd);
		return result;
	}
}
