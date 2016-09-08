package cn.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.shop.product.model.Product;
import cn.shop.utils.PageHibernateCallback;

/*
 * 产品模块持久层
 * 
 */

public class ProductDao extends HibernateDaoSupport {

	
	//热门商品查询方法
	public List<Product> findHot() {
		//离线条件查询
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		//热门商品查询条件is_hot==1
		criteria.add(Restrictions.eq("is_hot", 1));
		//使用倒序查询
		criteria.addOrder(Order.desc("pdate"));
		//执行查询
		List<Product> hList=this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return hList;
	}

	//最新商品查询方法
	public List<Product> findNew() {
		//离线查询
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		//按日期查询
		criteria.addOrder(Order.desc("pdate"));
		//执行查询
		List<Product> nList=this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return nList;
	}

	//商品详情查询
	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	//根据一级分类id，查询商品数目
	public int findCountByCid(Integer cid) {
		String hql="select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list=this.getHibernateTemplate().find(hql,cid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//根据一级分类id，商品起始位置，当前页数，分页查询商品
	public List<Product> findByCid(Integer cid, int begin, int limit) {
		String hql="select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	//根据二级分类查询商品个数
	public int findCountByCsid(Integer csid) {
		String hql="select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list=this.getHibernateTemplate().find(hql,csid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//根据二级分类查询商品
	public List<Product> findByCsid(Integer csid, int begin, int limit) {
		String hql="select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	//查询商品数量
	public int findCount() {
		String hql="select count(*) from Product";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//查询商品
	public List<Product> findAllByPage(int begin, int limit) {
		String hql="from Product order by pdate desc";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	//保存商品
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}
	//删除商品
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}
	//修改商品信息
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}

	
}
