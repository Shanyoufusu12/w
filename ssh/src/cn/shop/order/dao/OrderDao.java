package cn.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.shop.order.model.Order;
import cn.shop.order.model.OrderItem;
import cn.shop.product.model.Product;
import cn.shop.utils.PageHibernateCallback;
/*
 * 订单dao层
 * 
 */
public class OrderDao extends HibernateDaoSupport {

	//持久层保存订单
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}

	//持久层查询订单数量
	public int findCountByUid(Integer uid) {
		String hql="select count(*) from Order o where o.user.uid = ?";
		List<Long> list=this.getHibernateTemplate().find(hql,uid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//持久层根据uid查询订单
	public List<Order> findByUid(Integer uid, int begin, int limit) {
		String hql="from Order o where o.user.uid = ? order by ordertime desc";
		List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	//持久层根据oid查询订单
	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}
	//修改订单信息
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}
	//查询订单个数
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
	//查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql="from OrderItem oi where oi.order.oid = ?";
		List<OrderItem> list=this.getHibernateTemplate().find(hql,oid);
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
}
