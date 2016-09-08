package cn.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.shop.category.model.Category;
/*
 * һ������־ò����
 * 
 */
public class CategoryDao extends HibernateDaoSupport {

	//��ѯһ������
	public List<Category> findAll() {
		String hql="from Category";
		List<Category> cList=this.getHibernateTemplate().find(hql);
		return cList;
	}
	
	//���һ������
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}
	
	//����cid��ѯһ������
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class,cid);
	}
	//ɾ��һ������
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}
	//�޸�һ������
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}


	
}
