package cn.shop.adminuser.service;

import cn.shop.adminuser.dao.AdminUserDao;
import cn.shop.adminuser.model.AdminUser;

/*
 * ��̨����Ա��service
 * 
 */
public class AdminUserService {
	//ע��adminuserdao
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}
	//�����û��������ѯ�û�
	public AdminUser find(String username, String password) {
		return adminUserDao.find(username,password);
	}
	
}
