package cn.shop.product.adminproductaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.shop.categorysecond.model.CategorySecond;
import cn.shop.categorysecond.service.CategorySecondService;
import cn.shop.product.model.Product;
import cn.shop.product.service.ProductService;
import cn.shop.utils.PageBean;

public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {

	//�����ϴ��ļ�����
	private File upload;
	private String uploadFileName;
	private String uploadContextType;
	
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
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
	//ע��productservice
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	//ע��product
	private Product product=new Product();
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	//��ҳ��ѯ��Ʒ
	public String findAllByPage(){
		PageBean<Product> pageBean=productService.findAllByPage(page);
		//��pagebean����ֵջ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}

	//��ת�������Ʒҳ��
	public String addPage(){
		//��ѯ���ж�������
		List<CategorySecond> csList=categorySecondService.findAll();
		//�����ж����������ֵջ
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPage";
	}
	public String save() throws IOException{
		product.setPdate(new Date());
		if(upload != null){
			String realPath=ServletActionContext.getServletContext().getRealPath("/product");
			File diskFile=new File(realPath+"//"+uploadFileName);
			FileUtils.copyFile(upload, diskFile);
			product.setImage("product/"+uploadFileName);
		}
		productService.save(product);
		return "save";
	}
	//ɾ����Ʒ
	public String delete(){
		//��ѯ��Ʒ
		product=productService.findByPid(product.getPid());
		//ɾ����ƷͼƬ
		String path=product.getImage();
		if(path != null){
			String realPath=ServletActionContext.getServletContext().getRealPath("/"+path);
			File file=new File(realPath);
			file.delete();
		}
		//ɾ����Ʒ
		productService.delete(product);
		return "delete";
	}
	//�޸���Ʒҳ����ת
	public String edit(){
		//��ѯ��Ʒ
		product=productService.findByPid(product.getPid());
		//��ѯ��������
		List<CategorySecond> csList=categorySecondService.findAll();
		//�������������ֵջ��
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "edit";
	}
	//�޸���Ʒ
	public String update() throws IOException{
		//�޸���Ʒ��Ϣ
		product.setPdate(new Date());
		if(upload != null){
			// ɾ����ƷͼƬ
			String path = product.getImage();
			File file = new File( ServletActionContext.getServletContext().getRealPath("/" + path));
			file.delete();
			//�ϴ���ͼƬ
			String realPath=ServletActionContext.getServletContext().getRealPath("/product");
			File diskFile=new File(realPath+"//"+uploadFileName);
			FileUtils.copyFile(upload, diskFile);
			product.setImage("product/"+uploadFileName);
		}
		productService.update(product);
		return "update";
	}
}
