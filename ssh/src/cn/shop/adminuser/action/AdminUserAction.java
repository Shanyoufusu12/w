package cn.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.shop.adminuser.model.AdminUser;
import cn.shop.adminuser.service.AdminUserService;
/*
 * ��̨����Աaction
 * 
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser> {

	//ע��adminuser
	private AdminUser adminUser=new AdminUser();
	@Override
	public AdminUser getModel() {
		return adminUser;
	}
	
	//ע��adminuserservice
	private AdminUserService adminUserService;
	
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	//��̨��½
	public String login(){
		//�����û��������ѯ����
		AdminUser existAdminUser=adminUserService.find(adminUser.getUsername(),adminUser.getPassword());
		//�ж��Ƿ��ѯ�����û�
		if(existAdminUser == null){
		//δ��ѯ��
			this.addActionError("�û��������벻��ȷ��");
			return "loginError";
		}
		//��ѯ�������ݴ���session
		ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
		return "loginSuccess";
	}

}
