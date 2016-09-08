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
 * ��̨��������action
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {

	//ע��categoryservice
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//ע��categorysecond
	private CategorySecond categorySecond=new CategorySecond();
	@Override
	public CategorySecond getModel() {
		return categorySecond;
	}
	//ע��categorysecondservice
	private CategorySecondService categorySecondService;

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	//����page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	//��ҳ��ѯ��������
	public String findAllByPage(){
		PageBean<CategorySecond> pageBean=categorySecondService.findAllByPage(page);
		//����ѯ�õ����ݴ���ֵջ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}
	//��ѯһ������
	public String addPage(){
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPage";
	}
	//��Ӷ�������
	public String save(){
		categorySecondService.save(categorySecond);
		return "save";
	}
	//ɾ����������
	public String delete(){
		//�ȸ���csid��ѯ��������
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		//ɾ����������
		categorySecondService.delete(categorySecond);
		return "delete";
	}
	//��ת�����������޸�ҳ��
	public String edit(){
		//��ѯ��������
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		//��ѯһ������
		List<Category> cList=categoryService.findAll();
		//��һ���������ֵջ
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "edit";
	}
	//�޸Ķ�������
	public String update(){
		categorySecondService.update(categorySecond);
		return "update";
	}
}
