package ac.cn.chm.fj.mall.service.product.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ac.cn.chm.fj.mall.dao.base.DaoSupport;
import ac.cn.chm.fj.mall.service.product.ProductInfoService;
import ac.cn.chm.fj.util.init.Page;
import ac.cn.chm.fj.util.init.PageData;

@Service("productInfoService")
public class ProductInfoServiceImpl implements ProductInfoService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Override
	public int doCreate(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (int)this.dao.save("ProductInfoMapper.doCreate", pd);
	}

	@Override
	public int doUpdate(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (int)this.dao.update("ProductInfoMapper.doUpdate", pd);
	}

	@Override
	public int doRemove(String id) throws Exception {
		// TODO Auto-generated method stub
		PageData pd = new PageData();
		pd.put("productId", id);
		pd.put("productStatus", "D");
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
		return  (List<PageData>) this.dao.findForList("ProductInfoMapper.listPagePd", page);
		}

	@Override
	public PageData findPdById(String id) throws Exception {
		// TODO Auto-generated method stub
		return (PageData) this.dao.findForObject("ProductInfoMapper.findById", id);
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
