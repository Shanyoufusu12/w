package cn.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.shop.adminuser.model.AdminUser;
import cn.shop.adminuser.service.AdminUserService;
/*
 * 后台管理员action
 * 
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser> {

	//注入adminuser
	private AdminUser adminUser=new AdminUser();
	@Override
	public AdminUser getModel() {
		return adminUser;
	}
	
	//注入adminuserservice
	private AdminUserService adminUserService;
	
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	//后台登陆
	public String login(){
		//根据用户名密码查询数据
		AdminUser existAdminUser=adminUserService.find(adminUser.getUsername(),adminUser.getPassword());
		//判断是否查询到该用户
		if(existAdminUser == null){
		//未查询到
			this.addActionError("用户名或密码不正确！");
			return "loginError";
		}
		//查询到将数据存入session
		ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
		return "loginSuccess";
	}

}
