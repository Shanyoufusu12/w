package cn.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.product.dao.ProductDao;
import cn.shop.product.model.Product;
import cn.shop.utils.PageBean;

/*
 * ��Ʒģ��ҵ���
 * 
 */
@Transactional
public class ProductService {
	//ע��productdao
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	//������Ʒ��ѯ
	public List<Product> findHot() {
		return productDao.findHot();
	}

	//������Ʒ��ѯ
	public List<Product> findNew() {
		return productDao.findNew();
	}

	//��Ʒ�����ѯ
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}

	//����id��page��ѯһ��������Ʒ
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean=new PageBean<Product>();
		//����ÿҳ��Ʒ����
		int limit=16;
		pageBean.setLimit(limit);
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//������Ʒ����
		int totalCount=0;
		totalCount=productDao.findCountByCid(cid);
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage=0;
		//totalPage=(int) Math.ceil(totalCount/limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//��Ʒ��ʼ����
		int begin=(page-1)*limit;
		//������Ʒ
		List<Product> list=productDao.findByCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//���ݶ��������ѯ��Ʒ
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean=new PageBean<Product>();
		//����ÿҳ��Ʒ����
		int limit=8;
		pageBean.setLimit(limit);
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//������Ʒ����
		int totalCount=0;
		totalCount=productDao.findCountByCsid(csid);
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage=0;
		//totalPage=(int) Math.ceil(totalCount/limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//��Ʒ��ʼ����
		int begin=(page-1)*limit;
		//������Ʒ
		List<Product> list=productDao.findByCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//��ѯ��Ʒ
	public PageBean<Product> findAllByPage(Integer page) {
		PageBean<Product> pageBean=new PageBean<Product>();
		//����ÿҳ��Ʒ����
		int limit=10;
		pageBean.setLimit(limit);
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//������Ʒ����
		int totalCount=0;
		totalCount=productDao.findCount();
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage=0;
		//totalPage=(int) Math.ceil(totalCount/limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//��Ʒ��ʼ����
		int begin=(page-1)*limit;
		//������Ʒ
		List<Product> list=productDao.findAllByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//������Ʒ
	public void save(Product product) {
		productDao.save(product);
	}
	//ɾ����Ʒ
	public void delete(Product product) {
		productDao.delete(product);
	}
	//�޸���Ʒ��Ϣ
	public void update(Product product) {
		productDao.update(product);
	}
	
	
}
