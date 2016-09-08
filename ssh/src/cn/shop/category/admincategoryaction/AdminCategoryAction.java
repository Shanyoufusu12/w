package cn.shop.category.admincategoryaction;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.shop.category.model.Category;
import cn.shop.category.service.CategoryService;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {

	//注入category
	private Category category =new Category();
	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}
	//注入categoryservice
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//查询一级分类
	public String findAll(){
		//查询一级分类
		List<Category> cList=categoryService.findAll();
		//将查询结果存入session
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	//添加一级分类
	public String save(){
		categoryService.save(category);
		return "save";
	}
	//删除一级分类
	public String delete(){
		//根据cid查询一级分类
		category=categoryService.findByCid(category.getCid());
		//删除一级分类
		categoryService.delete(category);
		return "delete";
	}
	//跳转到修改页面
	public String edit(){
		//根据cid查询一级分类
		category = categoryService.findByCid(category.getCid());
		return "edit";
	}
	//修改一级分类
	public String update(){
		categoryService.update(category);
		return "update";
	}

}
