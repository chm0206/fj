package com.chm.fj.service.user;

import com.chm.fj.entity.UserInfo;
import com.chm.fj.service.base.IService;
import com.chm.fj.util.init.PageData;

public interface UserInfoService extends IService<UserInfo, String> {
	/**
	 * 获取用户的登录信息
	 * @param pd 传递userID/userPhone/userCard和密码
	 * @return
	 */
	PageData findUserLoginInfo(PageData pd) throws Exception;

}
