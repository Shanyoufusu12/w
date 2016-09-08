package cn.shop.categorysecond.service;

import java.util.List;

import cn.shop.categorysecond.dao.CategorySecondDao;
import cn.shop.categorysecond.model.CategorySecond;
import cn.shop.product.model.Product;
import cn.shop.utils.PageBean;

/*
 * ��̨���������ѯservice��
 * 
 */
public class CategorySecondService {

	// ע��categoryseconddao
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	// ҵ������ҳ���ѯ��������
	public PageBean<CategorySecond> findAllByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		// ����ÿҳ��Ʒ����
		int limit = 10;
		pageBean.setLimit(limit);
		// ���õ�ǰҳ��
		pageBean.setPage(page);
		// ������Ʒ����
		int totalCount = 0;
		totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		// ������ҳ��
		int totalPage = 0;
		//totalPage = (int) Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ��Ʒ��ʼ����
		int begin = (page - 1) * limit;
		// ������Ʒ
		List<CategorySecond> list = categorySecondDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
	//��Ӷ�������
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}
	//����csid��ѯ��������
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}
	//ɾ����������
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}
	//�޸Ķ�������
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

	//��ѯ���ж�������
	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}

}
