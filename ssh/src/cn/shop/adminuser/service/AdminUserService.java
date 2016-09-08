package cn.shop.adminuser.service;

import cn.shop.adminuser.dao.AdminUserDao;
import cn.shop.adminuser.model.AdminUser;

/*
 * 后台管理员的service
 * 
 */
public class AdminUserService {
	//注入adminuserdao
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}
	//根据用户名密码查询用户
	public AdminUser find(String username, String password) {
		return adminUserDao.find(username,password);
	}
	
}
