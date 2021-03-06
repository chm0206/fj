package ac.cn.chm.fj.uc.service.menu;

import java.util.List;

import ac.cn.chm.fj.uc.entity.MenuInfo;
import ac.cn.chm.fj.uc.service.base.IService;

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
