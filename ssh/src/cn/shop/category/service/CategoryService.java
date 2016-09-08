package cn.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.category.dao.CategoryDao;
import cn.shop.category.model.Category;

/*
 * һ������ҵ������
 * 
 */
@Transactional
public class CategoryService {
	//ע��categorydao
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	//��ѯһ������
	public List<Category> findAll() {
		return categoryDao.findAll();
	}
	//���һ������
	public void save(Category category) {
		categoryDao.save(category);
	}
	//����cid��ѯһ������
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}
	//ɾ��һ������
	public void delete(Category category) {
		categoryDao.delete(category);
	}
	//�޸�һ������
	public void update(Category category) {
		categoryDao.update(category);
	}
	
}
