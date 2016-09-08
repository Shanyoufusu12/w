package cn.shop.product.action;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.shop.category.service.CategoryService;
import cn.shop.product.model.Product;
import cn.shop.product.service.ProductService;
import cn.shop.utils.PageBean;
/*
 * 商品模块action对象
 * 
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product>{

	//注入categoryservice
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//注入product
	private Product product=new Product();
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	//获取cid
	private Integer cid;
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	public Integer getCid() {
		return cid;
	}
	//获取二级分类id：csid
	private Integer csid;
	
	
	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	//获取当前页
	private int page;
	
	public void setPage(int page) {
		this.page = page;
	}
	//注入productservice
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	//根据商品id查询商品详情
	public String findByPid(){
		product=productService.findByPid(product.getPid());
		return "findByPid";
	}
	//根据一级分类id查询商品
	public String findByCid(){
		//按照一级分类id查询商品；分页查询
		PageBean<Product> pageBean=productService.findByPageCid(cid,page);
		//将pagebean存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	//根据二级分类id查询商品
	public String findByCsid(){
		//按照二级分类查询商品；分页查询
		PageBean<Product> pageBean=productService.findByPageCsid(csid,page);
		//将pagebean存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
