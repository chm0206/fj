package com.chm.fj.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.SimpleHash;

import com.alibaba.fastjson.JSONObject;
import com.chm.fj.consts.ParamConst;
import com.chm.fj.util.init.PageData;

/** 
 * 说明：常用工具
 * 创建人：riseinfo.cn
 * 修改时间：2014年11月24日
 * @version
 */
public class Tools {
	protected Logger logger = Logger.getLogger(this.getClass().getName());
	/**
	 * 随机生成六位数验证码 
	 * @return
	 */
	public static int getRandomNum(){
		 Random r = new Random();
		 return r.nextInt(900000)+100000;//(Math.random()*(999999-100000)+100000)
	}
	
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}
	
	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}
	
	public static boolean isNull(Object obj){
		if(obj == null || "".equals(obj) || "null".equals(obj)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 字符串转换为字符串数组
	 * @param str 字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}
	
	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * @param str	字符串
	 * @return
	 */
	public static String[] str2StrArray(String str){
		return str2StrArray(str,",\\s*");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date,String format){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}
	
	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	
	/**
	 * 把时间根据时、分、秒转换为时间段
	 * @param StrDate
	 */
	public static String getTimes(String StrDate){
		String resultTimes = "";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    java.util.Date now;
	    
	    try {
	    	now = new Date();
	    	java.util.Date date=df.parse(StrDate);
	    	long times = now.getTime()-date.getTime();
	    	long day  =  times/(24*60*60*1000);
	    	long hour = (times/(60*60*1000)-day*24);
	    	long min  = ((times/(60*1000))-day*24*60-hour*60);
	    	long sec  = (times/1000-day*24*60*60-hour*60*60-min*60);
	        
	    	StringBuffer sb = new StringBuffer();
	    	//sb.append("发表于：");
	    	if(hour>0 ){
	    		sb.append(hour+"小时前");
	    	} else if(min>0){
	    		sb.append(min+"分钟前");
	    	} else{
	    		sb.append(sec+"秒前");
	    	}
	    		
	    	resultTimes = sb.toString();
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    
	    return resultTimes;
	}
	
	/**
	 * 写txt里的单行内容
	 * @param filePath  文件路径
	 * @param content  写入的内容
	 */
	public static void writeFile(String fileP,String content){
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
		filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
		if(filePath.indexOf(":") != 1){
			filePath = File.separator + filePath;
		}
		try {
	        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath),"utf-8");      
	        BufferedWriter writer=new BufferedWriter(write);          
	        writer.write(content);      
	        writer.close(); 

	        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	  * 验证邮箱
	  * @param email
	  * @return
	  */
	 public static boolean checkEmail(String email){
	  boolean flag = false;
	  try{
	    String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	    Pattern regex = Pattern.compile(check);
	    Matcher matcher = regex.matcher(email);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	
	 /**
	  * 验证手机号码
	  * @param mobiles
	  * @return
	  */
	 public static boolean checkMobileNumber(String mobileNumber){
	  boolean flag = false;
	  try{
	    Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
	    Matcher matcher = regex.matcher(mobileNumber);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	 
	/**
	 * 固定参数值的初始化为List<PageData>对象
	 * 分隔符为“|”
	 * @param parmStr
	 * @return List<PageData>
	 * @date 2016-07-16
	 */
	public static List<Map<String,Object>> splitParValue(String parmStr){
		List<Map<String,Object>> lpd = new ArrayList<Map<String,Object>>() ;
		if(!Tools.isEmpty(parmStr)){
			String[] sts = Tools.str2StrArray(parmStr, ParamConst.PARAM_REGEX) ;
			if(sts.length>0){
				for(int i=0;i<sts.length;i++){
					if(!Tools.isEmpty(sts[i])){
						//PageData npd = new PageData() ;
						Map<String,Object> npd = new HashMap<String,Object>() ;
						String[] result = sts[i].split(ParamConst.VALUE_REGEX) ;
						if(result.length == 2){
							npd.put("sId", result[0]) ;
							npd.put("sValue", result[1]) ;
						}else{
							npd.put("sId", "") ;
							npd.put("sValue", "") ;
						}
						lpd.add(npd) ;
					}
				}
			}
		}
		return lpd ;
	}
	
	/**
	 * 从pd对象中的fStr取值赋给tStr
	 * pd.put(tStr, pd.getString(fStr)) ;
	 * @param pd
	 * @param fStr
	 * @param tStr
	 * @date 2015年5月3日
	 * @return void
	 */
	public static void transEqVal(PageData pd,String tStr,String fStr){
		pd.put(tStr, pd.getString(fStr)) ;
	}
	
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	
	/**
	 * 根据登陆时间戳，生成accToken
	 * 16位字符串
	 * @return
	 * @throws Exception
	 */
	public static String greantAccToken(String nowTime) throws Exception {
		String tokenKey = ParamConst.TOKEN_KEY ;
		String accToken = new SimpleHash(ParamConst.SHA, nowTime , tokenKey).toString().substring(ParamConst.TOKEN_KEY_LEN).toUpperCase() ;
		return  accToken ;
	}
	
	/**
	 * 刷新时间的时候通过提交刷新的时间点，重新生成accToken
	 * 避免被复制，同时删除旧的accToken返回新的并更新
	 * @param refreshToken
	 * @param dteTime
	 * @return
	 * @throws Exception
	 */
	public static String refreshAccToken(String refreshToken,String dteTime) throws Exception {
		
		return null ;
	}
	
	/**
	 * 生成refreshToken
	 * 32位字符串
	 * 生成refreshToken30分钟后到1小时内，访问后台的时候，更换refreshToken
	 * @return
	 * @throws Exception
	 */
	public static String greantRefreshToken() throws Exception {
		return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "") ;
	}
	
	/**
	 * 加密Base64
	 * 用于用户ID加密
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String BaseEnCode(String str) throws Exception {
		if(Tools.notEmpty(str)){
			return Base64.encodeToString(str.getBytes()) ;
		}else{
			return null ;
		}
	}
	
	/**
	 * 解密 Base64
	 * 如果转换之后为空，则标识未加密，将原值返回
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String BaseDecCode(String str) throws Exception {
		if(Tools.notEmpty(str)){
			String result = Base64.decodeToString(str) ;
			return result ;
		}else{
			return null ;
		}
	}
	
	/**
	 * 解密前端传送的用户编号
	 * UID
	 * @param pd
	 * @return
	 *//*
	public static String DecUserID(PageData pd) {
		String jsid = pd.getString(ParamConst.UC_PARAM_JSID) ;
		if(notEmpty(jsid)){
			try {
				return BaseDecCode(jsid) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return null ;
			}
		}else if(notEmpty(pd.getString(ParamConst.FJ_USER_ID))){
				return pd.getString(ParamConst.FJ_USER_ID) ;
		}else
			return null ;
	}*/
	
	/**
	 * 对象属性拷贝
	 * @param targ
	 * @param from
	 */
	public static void CopyProperty(Object targ,Object from) {
		try {
			BeanUtils.copyProperties(targ, from);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Map to Bean
	 * @param map
	 * @param class1
	 * @author chm
	 * @return
	 */
	public static <T> T map2Bean(Map<String, String> map, Class<T> class1) {  
        T bean = null;  
        try {  
            bean = class1.newInstance();  
            BeanUtils.populate(bean, map);  
        } catch (InstantiationException e) {  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        } catch (InvocationTargetException e) {  
            e.printStackTrace();  
        }  
        return bean;  
    } 
	
	/**根据角色权限获取本权限的菜单列表(递归处理)
	 * @param menuList：传入的总菜单
	 * @param roleRights：加密的权限字符串
	 * @return
	 *//*
	public static List<MenuInfo> readMenu(List<MenuInfo> menuList,String roleRights){
		List<MenuInfo> resultList =  new ArrayList<MenuInfo>() ;
		for(int i=0;i<menuList.size();i++){
			MenuInfo menuInfo = menuList.get(i);
			menuInfo.setHasMenu(RightsHelper.testRights(roleRights, menuInfo.getMenuID()));
			if(menuInfo.isHasMenu()){		//判断是否有此菜单权限
				resultList.add(menuInfo) ;
				Tools.readMenu(menuInfo.getSubMenu(), roleRights);//是：继续排查其子菜单
			}
		}
		return resultList;
		for(int i=0;i<menuList.size();i++){
			menuList.get(i).setHasMenu(RightsHelper.testRights(roleRights, menuList.get(i).getMenuID()));
			if(menuList.get(i).isHasMenu()){		//判断是否有此菜单权限
				readMenu(menuList.get(i).getSubMenu(), roleRights);//是：继续排查其子菜单
			}else{
				menuList.remove(i);
				i--;
			}
		}
		return menuList;
	}
	
	*//**
	 * 转换按钮权限信息
	 * 特殊权限信息需要再维护进来
	 * @param qxList
	 * @param menuList
	 * @param roleRights
	 *//*
	public static void readQXList(List<String> qxList , List<MenuInfo> menuList,String roleRights){
		for(int i=0;i<menuList.size();i++){
			MenuInfo menuInfo = menuList.get(i);
			menuInfo.setHasMenu(RightsHelper.testRights(roleRights, menuInfo.getMenuID()));
			if(menuInfo.isHasMenu()){		//判断是否有此菜单权限
				qxList.add(menuList.get(i).getMenuID()) ;
				Tools.readQXList(qxList, menuInfo.getSubMenu(), roleRights);//是：继续排查其子菜单
			}
		}
	}
	
	*//***
	 * 
	 * @param menuList
	 * @param roleRights
	 * @return
	 *//*
	public static List<String> readQXList(List<MenuInfo> menuList,String roleRights) {
		List<String> qxList = new ArrayList<String>() ;
		for(int i=0;i<menuList.size();i++){
			if(RightsHelper.testRights(roleRights, menuList.get(i).getMenuID())){ //有权限true
				qxList.add(menuList.get(i).getMenuID()) ;
				Tools.readMenu(menuList.get(i).getSubMenu(), roleRights);//是：继续排查其子菜单
				//i--;
			}
		}
		return qxList ;
	}
	*/
	/**
	 * 判断s字符串中是否包含regx
	 * @param s
	 * @param regx
	 * @return
	 */
	public static boolean isMatcher(String s , String regx) {
		boolean isTrue = false ;
		Pattern r = Pattern.compile(regx);
		Matcher m = r.matcher(s);
		isTrue = m.find() ;
		return isTrue ;
	}

	/**
	 * 获取指定菜单的按钮权限
	 * @param menuQX
	 * @param menuID
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Object> getMenuQX(String menuQX,String menuID) throws Exception {
		Map<String,Object> resultMap = new HashMap<String,Object>() ;
		JSONObject json = new JSONObject() ;
		json = JSONObject.parseObject(menuQX).getJSONObject("menuQX") ;
		resultMap.put("menuID",menuID) ;
		if(isMatcher(fomatString(json.getString("qx_adds")),menuID)){
			resultMap.put("add","1") ;
		}else{
			resultMap.put("add","0") ;
		}
		
		if(isMatcher(fomatString(json.getString("qx_dels")),menuID)){
			resultMap.put("del","1") ;
		}else{
			resultMap.put("del","0") ;
		}
		
		if(isMatcher(fomatString(json.getString("qx_edits")),menuID)){
			resultMap.put("edit","1") ;
		}else{
			resultMap.put("edit","0") ;
		}
		
		if(isMatcher(fomatString(json.getString("qx_sears")),menuID)){
			resultMap.put("sear","1") ;
		}else{
			resultMap.put("sear","0") ;
		}
		
		return resultMap ;
	}
	
	/**
	 * 针对按钮权限的字符串的特定过滤方法
	 * @param str
	 * @return
	 */
	static String fomatString(String str) {
		String s = (str.replaceAll("\\[", "")).replaceAll("\\]", "") ;
		//去"
		s = s.replaceAll("\"", "") ;
		if(Tools.notEmpty(s)){
			return s ;
		}else{
			return null ;
		}
	}
	
	/***
	 * 自动获取并转换加密用户id赋值给userID
	 * @param parm
	 * @return
	 * @throws Exception
	 *//*
	public static String getUserID(ParamPojo parm) throws Exception {
		String userID = null ;
		if(Tools.notEmpty(parm.getUserID())){
			userID = parm.getUserID() ;
		}else{
			if(Tools.notEmpty(parm.getJsid()))
				userID = BaseDecCode(parm.getJsid()) ;
		}
		return userID ;
	}*/

	/***
	 * 格式化用户登录日志信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public static PageData formatLoginLog(PageData pd) throws Exception {
		
		
		return null ;
	}
	
	/***
	 * 格式化用户退出登录日志信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public static PageData formatLogoutLog(PageData pd) throws Exception {
		
		
		return null ;
	}
	
	
	public static void main(String[] args) {
		/*List<String> xx = new ArrayList<String>() ;
		for(int i=0 ; i<5 ; i++){
			
			xx.add(String.valueOf(i)) ;
		}
		System.out.println("xx " + xx);
		String[] yy = (String[])xx.toArray(new String[0]) ;
		
		System.out.println("yy " + yy.toString());
		for(String s : yy){
			System.out.println("ss" + s);
		}*/
		
		String UC_CHECK_AGEN = "userName,userAccount,userTel" ;
		String[] xxx = UC_CHECK_AGEN.split("\\,") ;
		for(String s:xxx){
			System.out.println("ssss " + s);
		}
	}
	
	
	
	/**
	 * 报文响应状态信息格式化方法
	 * @param code
	 * @param reason
	 * @return
	 *//*
	public static Map<String,Object> formStatus(String code,String msg) {
		Map<String,Object> isStatus = new HashMap<String,Object>() ;
		isStatus.put(ParamConst.UCAPI_STATUS_CODE, code) ; //状态码
		isStatus.put(ParamConst.UCAPI_STATUS_REASON, msg) ; //状态信息
		return isStatus ;
	}*/
}
