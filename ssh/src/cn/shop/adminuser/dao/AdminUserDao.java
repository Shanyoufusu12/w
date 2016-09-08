package cn.shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.shop.adminuser.model.AdminUser;
//��̨����Աdao
public class AdminUserDao extends HibernateDaoSupport {

	//�����û��������ѯ�û�
	public AdminUser find(String username, String password) {
		String hql="from AdminUser where username = ? and password = ?";
		List<AdminUser> list=this.getHibernateTemplate().find(hql,username,password);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	
}
