package cn.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.category.dao.CategoryDao;
import cn.shop.category.model.Category;

/*
 * 一级分类业务层对象
 * 
 */
@Transactional
public class CategoryService {
	//注入categorydao
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	//查询一级分类
	public List<Category> findAll() {
		return categoryDao.findAll();
	}
	//添加一级分类
	public void save(Category category) {
		categoryDao.save(category);
	}
	//根据cid查询一级分类
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}
	//删除一级分类
	public void delete(Category category) {
		categoryDao.delete(category);
	}
	//修改一级分类
	public void update(Category category) {
		categoryDao.update(category);
	}
	
}
