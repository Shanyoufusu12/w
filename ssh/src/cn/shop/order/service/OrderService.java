package cn.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.order.dao.OrderDao;
import cn.shop.order.model.Order;
import cn.shop.order.model.OrderItem;
import cn.shop.product.model.Product;
import cn.shop.utils.PageBean;

/*
 * 订单项service
 */
@Transactional
public class OrderService {
	//注入dao
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	
	//业务层保存订单
	public void save(Order order) {
		orderDao.save(order);
	}

	//业务层查询订单
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean=new PageBean<Order>();
		//设置每页商品数量
		int limit=5;
		pageBean.setLimit(limit);
		//设置当前页数
		pageBean.setPage(page);
		//设置商品总数
		int totalCount=0;
		totalCount=orderDao.findCountByUid(uid);
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
		List<Order> list=orderDao.findByUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
			
	}

	//业务层根据订单id查询订单
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}

	//修改订单信息
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}

	//分页查询订单
	public PageBean<Order> findAllByPage(Integer page) {
		PageBean<Order> pageBean=new PageBean<Order>();
		//设置每页商品数量
		int limit=10;
		pageBean.setLimit(limit);
		//设置当前页数
		pageBean.setPage(page);
		//设置商品总数
		int totalCount=0;
		totalCount=orderDao.findCount();
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
		List<Order> list=orderDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}
	
}
