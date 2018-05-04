package com.chm.fj.dao.base;

import java.util.List;

import com.chm.fj.util.Page;
import com.chm.fj.util.PageData;
/**
 * IDAO接口		
 * @author Administrator
 *
 * @param <K> 实体类
 * @param <V> 主键类型
 */
public interface IDAO<K,V> {
	/**
	 * 添加一条新的数据
	 * 
	 * @param pd
	 * @return
	 */
	int doCreate(PageData pd) throws Exception;

	/**
	 * 按条件编辑指定信息
	 * 
	 * @param pd
	 * @return
	 */
	int doUpdate(PageData pd) throws Exception;

	/**
	 * 根据ID逻辑删除指定数据
	 * 
	 * @param userID
	 * @return
	 */
	int doRemove(String id) throws Exception;
	/**
	 * 
	 * @param ids
	 * @return
	 */
	int doRemoves(String ids) throws Exception;

	/**
	 * 查询信息列表
	 * 
	 * @param page
	 * @return
	 */
	List<PageData> listPagePd(Page page) throws Exception;

	/**
	 * 根据id获取
	 * 
	 * @param userID
	 * @return
	 */
	PageData findPdById(String id) throws Exception;
	/**
	 * 根据id获取指定的信息
	 * @param userIDs
	 * @return
	 */
	List<PageData> listPagePdByIDs(String userIDs) throws Exception;

}
