package cn.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.user.dao.UserDao;
import cn.shop.user.model.User;
import cn.shop.utils.MailUtils;
import cn.shop.utils.UUIDUtils;
/*
 * �û�ģ��ҵ������
 * 
 */
@Transactional
public class UserService {
	//ע��UserDao
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	//���û�����ѯ�û�����
	public User findByName(String username){
		return userDao.findByName(username);
	}
	//�û�ע��
	public void save(User user) {
		user.setState(0);//״̬0��δ���1�����
		user.setCode(UUIDUtils.getUUID());
		userDao.save(user);
		//�����ʼ�
		MailUtils.sendMail(user.getEmail(), user.getCode());
	}
	//�û������֤����֤
	public User findByCode(String code) {
		User user = userDao.findByCode(code);
		return user; 
	}
	//�û�����ɹ����޸�״̬
	public void update(User existUser) {
		userDao.update(existUser);
	}

	//�û���¼
	public User login(User user) {
		return userDao.login(user);
	}
}
