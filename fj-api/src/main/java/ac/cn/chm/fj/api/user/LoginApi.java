package ac.cn.chm.fj.api.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

import ac.cn.chm.fj.api.util.ApiConst;
import ac.cn.chm.fj.api.util.HttpUtil;
import ac.cn.chm.fj.consts.CodeConst;
import ac.cn.chm.fj.consts.ParamConst;
import ac.cn.chm.fj.result.ResultInfo;
import ac.cn.chm.fj.util.init.PageData;

/**
 * 登录注销的api
 * 
 * @author chm
 * @data 2018-08-26
 */
public class LoginApi {
	private String accessKey;
	private String accessPass;

	public LoginApi(String accessKey, String accessPass) {
		// 系统是否可用校验
		// 暂时不知道怎么写好
		this.accessKey = accessKey;
		this.accessPass = accessPass;
	}

	/**
	 * 将accessKey、accessPass封装到pd中，便于访问时校验该系统是否有权限
	 * 
	 * @param pd
	 */
	private void addAccess(PageData pd) {
		pd.put("accesskey", this.accessKey);
		pd.put("accessPass", this.accessPass);
	}

	/**
	 * 校验当前用户是否已登录
	 * 
	 * @param pd
	 * @return 用户已登录，返回true,用户未登录，返回false
	 */
	public boolean isLogin(PageData pd) {
		// boolean isLogin = false;
		this.addAccess(pd);
		System.out.println(ApiConst.UC_CHECK_LOGIN);
		String result = HttpUtil.doPost(ApiConst.UC_CHECK_LOGIN, pd);
		ResultInfo ri = JSONObject.parseObject(result, ResultInfo.class);
		if (ri != null && CodeConst.CODE_NORMAL.equals(ri.getCode())) {
			return true;
		} else {
			return false;
		}
		// return isLogin;
	}

	public static void main(String[] args) {
		PageData pd = new PageData();
		pd.put(ParamConst.FJ_ACC_TOKEN, "9C906DB81E74A08C");
		/*LoginApi la = new LoginApi("accesskey", "accessPass");
		boolean isLogin = la.isLogin(pd);
		System.out.println(isLogin);*/
		System.out.println(new LoginApi("accessKey", "accessPass").getLoginInfo("9C906DB81E74A08C"));
	}
	/**
	 * 获取用户的基础信息
	 * @param accToken
	 * @return 获取不到数据则返回null
	 */
	public JSONObject getLoginInfo(String accToken) {
		// TODO Auto-generated method stub
		PageData pd = new PageData();
		pd.put(ParamConst.FJ_ACC_TOKEN, accToken);
		ResultInfo result = JSONObject.parseObject(HttpUtil.doPost(ApiConst.UC_LOGIN_INFO, pd),ResultInfo.class);
		pd.clear();//清除无用的数据
		return result.getData().getJSONObject("userInfo");
	}
	/**
	 * 单点登出（不确定是否可行）
	 * @param request
	 */
	public void ssoLogout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("userInfo");
	}
}
