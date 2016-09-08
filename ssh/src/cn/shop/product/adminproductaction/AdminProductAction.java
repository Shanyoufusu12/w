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

	//接收上传文件参数
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
	//注入productservice
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	//注入product
	private Product product=new Product();
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	//分页查询商品
	public String findAllByPage(){
		PageBean<Product> pageBean=productService.findAllByPage(page);
		//将pagebean存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}

	//跳转到添加商品页面
	public String addPage(){
		//查询所有二级分类
		List<CategorySecond> csList=categorySecondService.findAll();
		//将所有二级分类存入值栈
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
	//删除商品
	public String delete(){
		//查询商品
		product=productService.findByPid(product.getPid());
		//删除商品图片
		String path=product.getImage();
		if(path != null){
			String realPath=ServletActionContext.getServletContext().getRealPath("/"+path);
			File file=new File(realPath);
			file.delete();
		}
		//删除商品
		productService.delete(product);
		return "delete";
	}
	//修改商品页面跳转
	public String edit(){
		//查询商品
		product=productService.findByPid(product.getPid());
		//查询二级分类
		List<CategorySecond> csList=categorySecondService.findAll();
		//将二级分类存入值栈中
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "edit";
	}
	//修改商品
	public String update() throws IOException{
		//修改商品信息
		product.setPdate(new Date());
		if(upload != null){
			// 删除商品图片
			String path = product.getImage();
			File file = new File( ServletActionContext.getServletContext().getRealPath("/" + path));
			file.delete();
			//上传新图片
			String realPath=ServletActionContext.getServletContext().getRealPath("/product");
			File diskFile=new File(realPath+"//"+uploadFileName);
			FileUtils.copyFile(upload, diskFile);
			product.setImage("product/"+uploadFileName);
		}
		productService.update(product);
		return "update";
	}
}
