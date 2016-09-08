package cn.shop.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.shop.category.model.Category;
import cn.shop.category.service.CategoryService;
import cn.shop.product.model.Product;
import cn.shop.product.service.ProductService;

public class IndexAction extends ActionSupport {

	/**
	 * 访问首页的Action
	 */
	private static final long serialVersionUID = 1L;
	
	//注入一级分类service
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//注入产品service
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	@Override
	public String execute() throws Exception {
		//查询一级分类
		List<Category> cList=categoryService.findAll();
		//将查询查询结果存入session
		ActionContext.getContext().getSession().put("cList", cList);
		//查询热门商品
		List<Product> hList=productService.findHot();
		//将查询结果存入值栈中
		ActionContext.getContext().getValueStack().set("hList", hList);
		//查询最新商品
		List<Product> nList=productService.findNew();
		//将查询结果存入值栈中
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
	
}
