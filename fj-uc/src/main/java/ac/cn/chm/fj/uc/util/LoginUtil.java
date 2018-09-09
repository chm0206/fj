package ac.cn.chm.fj.uc.util;

import ac.cn.chm.fj.uc.redis.JedisCacheClient;
import ac.cn.chm.fj.util.CheckUtil;
import ac.cn.chm.fj.util.init.PageData;

public class LoginUtil {
	/**
	 * 校验用户是否已登录
	 * @param pd
	 * @param jedis
	 * @return
	 * @throws Exception
	 */
	public static boolean isLogin(PageData pd,JedisCacheClient jedis)throws Exception{
		if (CheckUtil.isEmpty(pd.getString("accToken"))) {
			//System.out.println("尚未登录，调到登录页面");
			return false;
		}
		// accToken是否已过期
		boolean unlisted = jedis.notExpire(pd.getString("accToken"), 0);
		if(unlisted){
			//System.out.println("登录过期，调到登录页面");
			return false;
		}
		//System.out.println("已登录");
		return true;
	}
}
