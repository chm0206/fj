package com.chm.fj.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.chm.fj.consts.ParamConst;
import com.chm.fj.consts.RedisConst;
import com.chm.fj.consts.StringConst;
import com.chm.fj.redis.JedisCacheClient;
import com.chm.fj.util.init.PageData;

public class RedisUtil {
	

	@Autowired
	private static JedisCacheClient jedis;
	
	public static void createLoginInfo(PageData pd){
		
		String refreshToken = Tools.greantRefreshToken();
		String accToekn = null;
		try {
			accToekn = Tools.greantAccToken();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pd.put(accToekn, accToekn);
		pd.put(StringConst.REDIS_REFRESH_TOKEN, refreshToken);
		pd.put(StringConst.REDIS_USER_INFO, pd.get(StringConst.REDIS_USER_INFO));
	}
	/**
	 * redis用户登录操作
	 * @param pd
	 * @throws Exception
	 */
	public static void userLogin(PageData pd) throws Exception{
		PageData userInfo = (PageData) pd.get(StringConst.REDIS_USER_INFO);
		String accToken = pd.getString(StringConst.REDIS_ACC_TOKEN);
		String accTokenValue = userInfo.getString(StringConst.USER_ID)+ParamConst.DIVISION_KOMMA+pd.getString(StringConst.OWNER_ID);
		String terminal = userInfo.getString(StringConst.USER_ID)+pd.getString(StringConst.SSO_TERMAINAL);//userID+用户登录终端
		String terminalValue = accToken+ParamConst.DIVISION_KOMMA+"";//accToken+""推送地址，暂没有想好
		if(jedis.isExpire(terminal, 0)){//如果有，清除旧的登录信息
			String str = jedis.getV(terminal, 0);
			String[] redisList = str.split(ParamConst.DIVISION_KOMMA);
			if(redisList.length >0){
				jedis.delKey(redisList[0], 0);
			}
		}
		jedis.setVExpire(terminal, terminalValue, RedisConst.EXPIRE_30_MINUTE, 0);
		jedis.setVExpire(accToken, accTokenValue, RedisConst.EXPIRE_30_MINUTE, 0);
		if(jedis.notExpire(userInfo.getString(StringConst.USER_ID), 0)){
			jedis.setVExpire(userInfo.getString(StringConst.USER_ID)+StringConst.REDIS_USER_INFO, userInfo, RedisConst.EXPIRE_30_MINUTE, 0);
		}
	}
}
