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
	 * ������ҳ��Action
	 */
	private static final long serialVersionUID = 1L;
	
	//ע��һ������service
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//ע���Ʒservice
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	@Override
	public String execute() throws Exception {
		//��ѯһ������
		List<Category> cList=categoryService.findAll();
		//����ѯ��ѯ�������session
		ActionContext.getContext().getSession().put("cList", cList);
		//��ѯ������Ʒ
		List<Product> hList=productService.findHot();
		//����ѯ�������ֵջ��
		ActionContext.getContext().getValueStack().set("hList", hList);
		//��ѯ������Ʒ
		List<Product> nList=productService.findNew();
		//����ѯ�������ֵջ��
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
	
}
