package cn.shop.product.action;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.shop.category.service.CategoryService;
import cn.shop.product.model.Product;
import cn.shop.product.service.ProductService;
import cn.shop.utils.PageBean;
/*
 * ��Ʒģ��action����
 * 
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product>{

	//ע��categoryservice
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//ע��product
	private Product product=new Product();
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	//��ȡcid
	private Integer cid;
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	public Integer getCid() {
		return cid;
	}
	//��ȡ��������id��csid
	private Integer csid;
	
	
	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	//��ȡ��ǰҳ
	private int page;
	
	public void setPage(int page) {
		this.page = page;
	}
	//ע��productservice
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	//������Ʒid��ѯ��Ʒ����
	public String findByPid(){
		product=productService.findByPid(product.getPid());
		return "findByPid";
	}
	//����һ������id��ѯ��Ʒ
	public String findByCid(){
		//����һ������id��ѯ��Ʒ����ҳ��ѯ
		PageBean<Product> pageBean=productService.findByPageCid(cid,page);
		//��pagebean����ֵջ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	//���ݶ�������id��ѯ��Ʒ
	public String findByCsid(){
		//���ն��������ѯ��Ʒ����ҳ��ѯ
		PageBean<Product> pageBean=productService.findByPageCsid(csid,page);
		//��pagebean����ֵջ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
