package ac.cn.chm.fj.uc.service.user;

import ac.cn.chm.fj.uc.entity.UserInfo;
import ac.cn.chm.fj.uc.service.base.IService;
import ac.cn.chm.fj.util.init.PageData;

public interface UserInfoService extends IService<UserInfo, String> {
	/**
	 * 获取用户的登录信息
	 * @param pd 传递userID/userPhone/userCard和密码
	 * @return
	 */
	PageData findUserLoginInfo(PageData pd) throws Exception;

}
