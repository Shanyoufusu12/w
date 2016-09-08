package cn.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.user.dao.UserDao;
import cn.shop.user.model.User;
import cn.shop.utils.MailUtils;
import cn.shop.utils.UUIDUtils;
/*
 * 用户模块业务层代码
 * 
 */
@Transactional
public class UserService {
	//注入UserDao
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	//按用户名查询用户方法
	public User findByName(String username){
		return userDao.findByName(username);
	}
	//用户注册
	public void save(User user) {
		user.setState(0);//状态0：未激活；1：激活；
		user.setCode(UUIDUtils.getUUID());
		userDao.save(user);
		//发送邮件
		MailUtils.sendMail(user.getEmail(), user.getCode());
	}
	//用户激活，验证码验证
	public User findByCode(String code) {
		User user = userDao.findByCode(code);
		return user; 
	}
	//用户激活成功，修改状态
	public void update(User existUser) {
		userDao.update(existUser);
	}

	//用户登录
	public User login(User user) {
		return userDao.login(user);
	}
}
