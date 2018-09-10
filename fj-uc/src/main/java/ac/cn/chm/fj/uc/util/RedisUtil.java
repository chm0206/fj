package ac.cn.chm.fj.uc.util;

import ac.cn.chm.fj.consts.ParamConst;
import ac.cn.chm.fj.consts.StringConst;
import ac.cn.chm.fj.uc.redis.JedisCacheClient;
import ac.cn.chm.fj.util.CheckUtil;
import ac.cn.chm.fj.util.Tools;
import ac.cn.chm.fj.util.init.PageData;

public class RedisUtil {
	
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
	public static void userLogin(JedisCacheClient jedis,PageData pd) throws Exception{
		PageData userInfo = (PageData) pd.get(StringConst.REDIS_USER_INFO);
		//accToken_key
		String accToken = pd.getString(StringConst.REDIS_ACC_TOKEN);
		//accToken_value:userID,ownerID,SSO_TERMAINAL(登录终端)
		String accTokenValue = userInfo.getString(StringConst.USER_ID)+ParamConst.DIV_KOMMA+userInfo.getString(StringConst.OWNER_ID)+ParamConst.DIV_KOMMA+pd.getString(StringConst.SSO_TERMAINAL);
		//终端_key:userID+终端类型
		String terminal = userInfo.getString(StringConst.USER_ID)+pd.getString(StringConst.SSO_TERMAINAL);//userID+用户登录终端
		//终端_value
		String terminalValue = accToken+ParamConst.DIV_KOMMA+"";//accToken+""推送地址，暂没有想好
		if(jedis.isExpire(terminal, 0)){//如果有，清除旧的登录信息
			String str = jedis.getV(terminal, 0);//str:accToken_key+推送地址等
			String[] redisList = str.split(ParamConst.DIV_KOMMA);
			if(redisList.length >0){
				jedis.delKey(redisList[0], 0);
			}
		}
		//设置过期时间
		//添加登录终端
		jedis.setVExpire(terminal, terminalValue, ParamConst.EXPIRE_30_MINUTE, 0);
		//添加accToken
		jedis.setVExpire(accToken, accTokenValue, ParamConst.EXPIRE_30_MINUTE, 0);
		if(jedis.notExpire(userInfo.getString(StringConst.USER_ID), 0)){
			jedis.setVExpire(userInfo.getString(StringConst.USER_ID)+StringConst.REDIS_USER_INFO, userInfo, ParamConst.EXPIRE_30_MINUTE, 0);
		}
	}
	/**
	 * redis用户登出操作
	 * @param jedis
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public static boolean userLogout(JedisCacheClient jedis,PageData pd) throws Exception{
		boolean result = false;
		if(CheckUtil.isEmpty(pd.getString("accToken"))){//如果为空，表示已退出
			return true;
		}
		String accToken = pd.getString("accToken");
		if(jedis.isExpire(accToken, 0)){//获取
			String str = jedis.getV(accToken, 0);//str:accToken_key+推送地址等
			String[] redisList = str.split(ParamConst.DIV_KOMMA);
			if(redisList.length >0){
				try{
				jedis.delKey(redisList[0], 0);
				jedis.delKey(redisList[0], 0);
				}catch(Exception e){
					//异常表示登出操作失败
					e.printStackTrace();
					return false;
				}
			}
		}else{
			return true;//已退出
		}
		return result;
	}
}
