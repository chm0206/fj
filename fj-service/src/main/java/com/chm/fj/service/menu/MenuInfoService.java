package com.chm.fj.service.menu;

import java.util.List;

import com.chm.fj.entity.MenuInfo;
import com.chm.fj.service.base.IService;
import com.chm.fj.util.init.PageData;

public interface MenuInfoService extends IService<MenuInfo, String> {
	/**
	 * 获取所有菜单（单例）
	 * @return
	 * @throws Exception
	 */
	public List<MenuInfo> getAllMenu() throws Exception;
	/**
	 * 获取菜单ID下的所有菜单信息
	 * @param menuID
	 * @return
	 * @throws Exception
	 */
	List<MenuInfo> listAllMenu(String menuID) throws Exception;
	/**
	 * 根据父ID获取菜单信息
	 * @param parentID
	 * @return
	 * @throws Exception
	 */
 	List<MenuInfo> getChildMenu(String parentID) throws Exception;
}
