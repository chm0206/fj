package com.chm.fj.util;

import com.chm.fj.consts.StringConst;
import com.chm.fj.util.init.PageData;

public class UserUtil {
	/**
	 * 校验登录信息是否完整
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public boolean checkUserInfo(PageData pd) throws Exception{
		if(CheckUtil.isEmpty(pd.getString(StringConst.USER_PASS))){
			return false;
		}
		if(CheckUtil.notEmpty(pd.getString(StringConst.USER_ACCOUNT))
		||CheckUtil.notEmpty(pd.getString(StringConst.USER_ID))){
			return true;
		}else{
			return false;
		}
	}
}
