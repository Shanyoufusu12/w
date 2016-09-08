package cn.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.shop.user.model.User;

/*
 * �û�ģ��־ò����
 * 
 */

public class UserDao extends HibernateDaoSupport{
	//��֤�û����Ƿ����
	public User findByName(String username){
		String hql="from User where username = ?";
		List<User> userList=this.getHibernateTemplate().find(hql, username);
		if(userList != null && userList.size() > 0){
			return userList.get(0);
		}
			return null;
	}
	//ע���û���Ϣ
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}
	//�û�������֤������
	public User findByCode(String code) {
		String hql="from User where code = ?";
		List<User> userList=this.getHibernateTemplate().find(hql,code);
		if(userList != null && userList.size() > 0){
			return userList.get(0);
		}
			return null;
	}
	//����ɹ����޸��û�״̬
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
	}
	//�û���¼��֤
	public User login(User user) {
		String hql="from User where username = ? and password = ? and state = ?";
		List<User> userList=this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword(),1);
		if(userList != null && userList.size() > 0){
			return userList.get(0);
		}
			return null;
	}
	
}
