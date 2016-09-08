package cn.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.shop.category.model.Category;
/*
 * 一级分类持久层对象
 * 
 */
public class CategoryDao extends HibernateDaoSupport {

	//查询一级分类
	public List<Category> findAll() {
		String hql="from Category";
		List<Category> cList=this.getHibernateTemplate().find(hql);
		return cList;
	}
	
	//添加一级分类
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}
	
	//根据cid查询一级分类
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class,cid);
	}
	//删除一级分类
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}
	//修改一级分类
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}


	
}
