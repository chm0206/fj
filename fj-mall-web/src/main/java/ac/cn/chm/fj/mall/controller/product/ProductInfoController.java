package ac.cn.chm.fj.mall.controller.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ac.cn.chm.fj.mall.controller.base.BaseController;
import ac.cn.chm.fj.mall.service.product.ProductInfoService;
import ac.cn.chm.fj.result.ResultInfo;
import ac.cn.chm.fj.util.init.Page;
import ac.cn.chm.fj.util.init.PageData;

@RestController
@RequestMapping(value = "pro")
public class ProductInfoController extends BaseController {
	@Resource(name = "productInfoService")
	private ProductInfoService productInfoService;

	/**
	 * 获取商品列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list")
	public ResultInfo productList(Page page) throws Exception {
		ResultInfo result = new ResultInfo();
		PageData pd = this.getPageData();
		page.setPd(pd);
		List<PageData> pList = this.productInfoService.listPagePd(page);
		result.put("products", pList);
		return result;
	}
}
