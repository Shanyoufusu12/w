package cn.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.shop.product.model.Product;
import cn.shop.utils.PageHibernateCallback;

/*
 * ��Ʒģ��־ò�
 * 
 */

public class ProductDao extends HibernateDaoSupport {

	
	//������Ʒ��ѯ����
	public List<Product> findHot() {
		//����������ѯ
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		//������Ʒ��ѯ����is_hot==1
		criteria.add(Restrictions.eq("is_hot", 1));
		//ʹ�õ����ѯ
		criteria.addOrder(Order.desc("pdate"));
		//ִ�в�ѯ
		List<Product> hList=this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return hList;
	}

	//������Ʒ��ѯ����
	public List<Product> findNew() {
		//���߲�ѯ
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		//�����ڲ�ѯ
		criteria.addOrder(Order.desc("pdate"));
		//ִ�в�ѯ
		List<Product> nList=this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return nList;
	}

	//��Ʒ�����ѯ
	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	//����һ������id����ѯ��Ʒ��Ŀ
	public int findCountByCid(Integer cid) {
		String hql="select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list=this.getHibernateTemplate().find(hql,cid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//����һ������id����Ʒ��ʼλ�ã���ǰҳ������ҳ��ѯ��Ʒ
	public List<Product> findByCid(Integer cid, int begin, int limit) {
		String hql="select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	//���ݶ��������ѯ��Ʒ����
	public int findCountByCsid(Integer csid) {
		String hql="select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list=this.getHibernateTemplate().find(hql,csid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//���ݶ��������ѯ��Ʒ
	public List<Product> findByCsid(Integer csid, int begin, int limit) {
		String hql="select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	//��ѯ��Ʒ����
	public int findCount() {
		String hql="select count(*) from Product";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//��ѯ��Ʒ
	public List<Product> findAllByPage(int begin, int limit) {
		String hql="from Product order by pdate desc";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	//������Ʒ
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}
	//ɾ����Ʒ
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}
	//�޸���Ʒ��Ϣ
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}

	
}
