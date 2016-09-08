package cn.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.product.dao.ProductDao;
import cn.shop.product.model.Product;
import cn.shop.utils.PageBean;

/*
 * 产品模块业务层
 * 
 */
@Transactional
public class ProductService {
	//注入productdao
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	//热门商品查询
	public List<Product> findHot() {
		return productDao.findHot();
	}

	//最新商品查询
	public List<Product> findNew() {
		return productDao.findNew();
	}

	//商品详情查询
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}

	//根据id，page查询一级分类商品
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean=new PageBean<Product>();
		//设置每页商品数量
		int limit=16;
		pageBean.setLimit(limit);
		//设置当前页数
		pageBean.setPage(page);
		//设置商品总数
		int totalCount=0;
		totalCount=productDao.findCountByCid(cid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		//totalPage=(int) Math.ceil(totalCount/limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//商品起始坐标
		int begin=(page-1)*limit;
		//设置商品
		List<Product> list=productDao.findByCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//根据二级分类查询商品
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean=new PageBean<Product>();
		//设置每页商品数量
		int limit=8;
		pageBean.setLimit(limit);
		//设置当前页数
		pageBean.setPage(page);
		//设置商品总数
		int totalCount=0;
		totalCount=productDao.findCountByCsid(csid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		//totalPage=(int) Math.ceil(totalCount/limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//商品起始坐标
		int begin=(page-1)*limit;
		//设置商品
		List<Product> list=productDao.findByCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//查询商品
	public PageBean<Product> findAllByPage(Integer page) {
		PageBean<Product> pageBean=new PageBean<Product>();
		//设置每页商品数量
		int limit=10;
		pageBean.setLimit(limit);
		//设置当前页数
		pageBean.setPage(page);
		//设置商品总数
		int totalCount=0;
		totalCount=productDao.findCount();
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		//totalPage=(int) Math.ceil(totalCount/limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//商品起始坐标
		int begin=(page-1)*limit;
		//设置商品
		List<Product> list=productDao.findAllByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//保存商品
	public void save(Product product) {
		productDao.save(product);
	}
	//删除商品
	public void delete(Product product) {
		productDao.delete(product);
	}
	//修改商品信息
	public void update(Product product) {
		productDao.update(product);
	}
	
	
}
