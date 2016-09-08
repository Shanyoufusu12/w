package cn.shop.categorysecond.service;

import java.util.List;

import cn.shop.categorysecond.dao.CategorySecondDao;
import cn.shop.categorysecond.model.CategorySecond;
import cn.shop.product.model.Product;
import cn.shop.utils.PageBean;

/*
 * 后台二级分类查询service层
 * 
 */
public class CategorySecondService {

	// 注入categoryseconddao
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	// 业务层根据页面查询二级分类
	public PageBean<CategorySecond> findAllByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		// 设置每页商品数量
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置当前页数
		pageBean.setPage(page);
		// 设置商品总数
		int totalCount = 0;
		totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		//totalPage = (int) Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 商品起始坐标
		int begin = (page - 1) * limit;
		// 设置商品
		List<CategorySecond> list = categorySecondDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
	//添加二级分类
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}
	//根据csid查询二级分类
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}
	//删除二级分类
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}
	//修改二级分类
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

	//查询所有二级分类
	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}

}
