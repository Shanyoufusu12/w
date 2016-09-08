package cn.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.shop.user.model.User;

/*
 * 用户模块持久层代码
 * 
 */

public class UserDao extends HibernateDaoSupport{
	//验证用户名是否存在
	public User findByName(String username){
		String hql="from User where username = ?";
		List<User> userList=this.getHibernateTemplate().find(hql, username);
		if(userList != null && userList.size() > 0){
			return userList.get(0);
		}
			return null;
	}
	//注册用户信息
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}
	//用户激活验证激活码
	public User findByCode(String code) {
		String hql="from User where code = ?";
		List<User> userList=this.getHibernateTemplate().find(hql,code);
		if(userList != null && userList.size() > 0){
			return userList.get(0);
		}
			return null;
	}
	//激活成功，修改用户状态
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
	}
	//用户登录验证
	public User login(User user) {
		String hql="from User where username = ? and password = ? and state = ?";
		List<User> userList=this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword(),1);
		if(userList != null && userList.size() > 0){
			return userList.get(0);
		}
			return null;
	}
	
}
