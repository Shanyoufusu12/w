package cn.shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.shop.adminuser.model.AdminUser;
//后台管理员dao
public class AdminUserDao extends HibernateDaoSupport {

	//根据用户名密码查询用户
	public AdminUser find(String username, String password) {
		String hql="from AdminUser where username = ? and password = ?";
		List<AdminUser> list=this.getHibernateTemplate().find(hql,username,password);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	
}
