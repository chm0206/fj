package ac.cn.chm.fj.uc.service.menu.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ac.cn.chm.fj.consts.ParamConst;
import ac.cn.chm.fj.uc.dao.base.DaoSupport;
import ac.cn.chm.fj.uc.entity.MenuInfo;
import ac.cn.chm.fj.uc.service.menu.MenuInfoService;
import ac.cn.chm.fj.util.CheckUtil;
import ac.cn.chm.fj.util.init.Page;
import ac.cn.chm.fj.util.init.PageData;

/**
 * 用户信息表
 * 
 * @author Administrator
 *
 */
@Service("menuInfoService")
public class MenuInfoServiceImpl implements MenuInfoService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	private static List<MenuInfo> allMenu = null;

	private static int menuCount = 0;

	@Override
	public int doCreate(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (int) this.dao.save("MenuInfoMapper.doCreate", pd);
	}

	@Override
	public int doUpdate(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (int) this.dao.update("MenuInfoMapper.doUpdate", pd);
	}

	@Override
	public int doRemove(String id) throws Exception {
		// TODO Auto-generated method stub
		PageData pd = new PageData();
		pd.put("userID", id);
		pd.put("userStatus", "D");
		return this.doUpdate(pd);
		// return (int) this.dao.update("UserInfoMapper.editUserInfo", pd);
		// return (int)this.dao.delete("UserInfoMapper.delUserInfo", pd);
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
		// return (int) this.dao.update("UserInfoMapper.editUserInfo", pd);
		// return (int) this.dao.update("UserInfoMapper.editUserInfo", pd);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> listPagePd(Page page) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>) this.dao.findForList("MenuInfoMapper.listPagePd", page);
	}

	@Override
	public PageData findPdById(String id) throws Exception {
		// TODO Auto-generated method stub
		return (PageData) this.dao.findForObject("MenuInfoMapper.findById", id);
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
	public List<MenuInfo> listAllMenu(String menuID) throws Exception {
		List<MenuInfo> allMenu = this.getChildMenu(menuID);
		for (MenuInfo menuInfo : allMenu) {
			menuInfo.setChildMenu(this.listAllMenu(menuInfo.getMenuID()));
		}
		return allMenu;
	}

	@SuppressWarnings("static-access")
	public List<MenuInfo> getAllMenu() throws Exception {
		String rootID = ParamConst.MENU_ROOT_ID;
		int count = (int) this.dao.findForObject("MenuInfoMapper.getCountByStatus", ParamConst.MENU_STATUS_NORMAL);
		if (CheckUtil.isEmpty(this.allMenu) || this.menuCount != count) {
			this.allMenu = listAllMenu(rootID);
			this.menuCount = count;
		}
		return this.allMenu;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuInfo> getChildMenu(String parentID) throws Exception {
		// TODO Auto-generated method stub
		return (List<MenuInfo>) this.dao.findForList("MenuInfoMapper.listAllMenu", parentID);
	}
}
