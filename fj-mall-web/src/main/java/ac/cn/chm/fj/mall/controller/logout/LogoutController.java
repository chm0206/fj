package ac.cn.chm.fj.mall.controller.logout;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import ac.cn.chm.fj.mall.controller.base.BaseController;
import ac.cn.chm.fj.util.init.PageData;
@Controller
@RequestMapping(value = "sso")
public class LogoutController extends BaseController {
	/**
	 * 单点登出（删除用户信息，也不知道是可以还是不可以）
	 * @throws Exception
	 */
	@RequestMapping(value = "logout")
	public void productList() throws Exception {
		PageData pd = this.getPageData();
		HttpSession session = pd.getRequest().getSession();
		JSONObject o = (JSONObject) session.getAttribute("userInfo");
		session.removeAttribute("userInfo");
	}
}
