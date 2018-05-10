package com.chm.fj.service.menu;

import java.util.List;

import com.chm.fj.entity.MenuInfo;
import com.chm.fj.service.base.IService;
import com.chm.fj.util.PageData;

public interface MenuInfoService extends IService<MenuInfo, String> {
	List<PageData> listAllMenu(String menuID) throws Exception;
}
