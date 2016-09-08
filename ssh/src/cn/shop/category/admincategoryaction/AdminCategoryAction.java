package cn.shop.category.admincategoryaction;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.shop.category.model.Category;
import cn.shop.category.service.CategoryService;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {

	//ע��category
	private Category category =new Category();
	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}
	//ע��categoryservice
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//��ѯһ������
	public String findAll(){
		//��ѯһ������
		List<Category> cList=categoryService.findAll();
		//����ѯ�������session
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	//���һ������
	public String save(){
		categoryService.save(category);
		return "save";
	}
	//ɾ��һ������
	public String delete(){
		//����cid��ѯһ������
		category=categoryService.findByCid(category.getCid());
		//ɾ��һ������
		categoryService.delete(category);
		return "delete";
	}
	//��ת���޸�ҳ��
	public String edit(){
		//����cid��ѯһ������
		category = categoryService.findByCid(category.getCid());
		return "edit";
	}
	//�޸�һ������
	public String update(){
		categoryService.update(category);
		return "update";
	}

}
