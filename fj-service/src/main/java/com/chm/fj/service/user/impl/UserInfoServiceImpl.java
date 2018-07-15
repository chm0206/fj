package com.chm.fj.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chm.fj.consts.StringConst;
import com.chm.fj.dao.base.DaoSupport;
import com.chm.fj.service.user.UserInfoService;
import com.chm.fj.util.CheckUtil;
import com.chm.fj.util.Tools;
import com.chm.fj.util.init.Page;
import com.chm.fj.util.init.PageData;

/**
 * 用户信息表
 * 
 * @author Administrator
 *
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@Override
	public int doCreate(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (int) this.dao.save("UserInfoMapper.doCreate", pd);
	}

	@Override
	public int doUpdate(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (int) this.dao.update("UserInfoMapper.doUpdate", pd);
	}

	@Override
	public int doRemove(String id) throws Exception {
		// TODO Auto-generated method stub
		PageData pd = new PageData();
		pd.put("userID", id);
		pd.put("userStatus", "D");
		return this.doUpdate(pd);
	}

	@Override
	public int doRemoves(String ids) throws Exception {
		// TODO Auto-generated method stub
		String[] list = ids.split(",");
		int count = 0;
		for (String id : list) {
			count += this.doRemove(id);
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> listPagePd(Page page) throws Exception {
		// TODO Auto-generated method stub
		return  (List<PageData>) this.dao.findForList("UserInfoMapper.listPagePd", page);
	}

	@Override
	public PageData findPdById(String id) throws Exception {
		// TODO Auto-generated method stub
		return (PageData) this.dao.findForObject("UserInfoMapper.findPdById", id);
	}

	@Override
	public List<PageData> listPagePdByIDs(String userIDs) throws Exception {
		// TODO Auto-generated method stub
		PageData pd = new PageData();
		pd.put("userIDs", userIDs);
		Page page = new Page();
		page.setPd(pd);
		return this.listPagePd(page);
	}

	@Override
	public PageData findUserLoginInfo(PageData pd) throws Exception{
		// TODO Auto-generated method stub
		if(!checkUserInfo(pd)){
			return null;
		}
		return (PageData) this.dao.findForObject("UserInfoMapper.findUserLoginInfo", pd);
	}
	/**
	 * 校验登录信息是否完整
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	private boolean checkUserInfo(PageData pd) throws Exception{
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
