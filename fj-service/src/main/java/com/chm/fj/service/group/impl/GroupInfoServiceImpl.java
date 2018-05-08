package com.chm.fj.service.group.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chm.fj.dao.base.DaoSupport;
import com.chm.fj.service.group.GroupInfoService;
import com.chm.fj.util.Page;
import com.chm.fj.util.PageData;

/**
 * 用户信息表
 * 
 * @author Administrator
 *
 */
@Service("groupInfoService")
public class GroupInfoServiceImpl implements GroupInfoService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@Override
	public int doCreate(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (int) this.dao.save("GroupInfoMapper.doCreate", pd);
	}

	@Override
	public int doUpdate(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (int) this.dao.update("GroupInfoMapper.doUpdate", pd);
	}

	@Override
	public int doRemove(String id) throws Exception {
		// TODO Auto-generated method stub
		PageData pd = new PageData();
		pd.put("userID", id);
		pd.put("userStatus", "D");
		return this.doUpdate(pd);
		//return (int) this.dao.update("UserInfoMapper.editUserInfo", pd);
		//return (int)this.dao.delete("UserInfoMapper.delUserInfo", pd);
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
		//return (int) this.dao.update("UserInfoMapper.editUserInfo", pd);
		//return (int) this.dao.update("UserInfoMapper.editUserInfo", pd);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> listPagePd(Page page) throws Exception {
		// TODO Auto-generated method stub
		return  (List<PageData>) this.dao.findForList("GroupInfoMapper.listPagePd", page);
	}

	@Override
	public PageData findPdById(String id) throws Exception {
		// TODO Auto-generated method stub
		return (PageData) this.dao.findForObject("GroupInfoMapper.findById", id);
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
}
