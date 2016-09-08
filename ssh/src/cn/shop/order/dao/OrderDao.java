package cn.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.shop.order.model.Order;
import cn.shop.order.model.OrderItem;
import cn.shop.product.model.Product;
import cn.shop.utils.PageHibernateCallback;
/*
 * ����dao��
 * 
 */
public class OrderDao extends HibernateDaoSupport {

	//�־ò㱣�涩��
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}

	//�־ò��ѯ��������
	public int findCountByUid(Integer uid) {
		String hql="select count(*) from Order o where o.user.uid = ?";
		List<Long> list=this.getHibernateTemplate().find(hql,uid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//�־ò����uid��ѯ����
	public List<Order> findByUid(Integer uid, int begin, int limit) {
		String hql="from Order o where o.user.uid = ? order by ordertime desc";
		List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	//�־ò����oid��ѯ����
	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}
	//�޸Ķ�����Ϣ
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}
	//��ѯ��������
	public int findCount() {
		String hql="select count(*) from Order";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Order> findByPage(int begin, int limit) {
		String hql="from Order order by ordertime desc";
		List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	//��ѯ������
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql="from OrderItem oi where oi.order.oid = ?";
		List<OrderItem> list=this.getHibernateTemplate().find(hql,oid);
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
}
