package cn.shop.categorysecond.admincategorysecondaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.shop.category.model.Category;
import cn.shop.category.service.CategoryService;
import cn.shop.categorysecond.model.CategorySecond;
import cn.shop.categorysecond.service.CategorySecondService;
import cn.shop.utils.PageBean;

/*
 * 后台二级分类action
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {

	//注入categoryservice
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//注入categorysecond
	private CategorySecond categorySecond=new CategorySecond();
	@Override
	public CategorySecond getModel() {
		return categorySecond;
	}
	//注入categorysecondservice
	private CategorySecondService categorySecondService;

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	//接收page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	//分页查询二级分类
	public String findAllByPage(){
		PageBean<CategorySecond> pageBean=categorySecondService.findAllByPage(page);
		//将查询得到数据存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}
	//查询一级分类
	public String addPage(){
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPage";
	}
	//添加二级分类
	public String save(){
		categorySecondService.save(categorySecond);
		return "save";
	}
	//删除二级分类
	public String delete(){
		//先根据csid查询二级分类
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		//删除二级分类
		categorySecondService.delete(categorySecond);
		return "delete";
	}
	//跳转到二级分类修改页面
	public String edit(){
		//查询二级分类
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		//查询一级分类
		List<Category> cList=categoryService.findAll();
		//将一级分类存入值栈
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "edit";
	}
	//修改二级分类
	public String update(){
		categorySecondService.update(categorySecond);
		return "update";
	}
}
