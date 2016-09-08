package cn.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.order.dao.OrderDao;
import cn.shop.order.model.Order;
import cn.shop.order.model.OrderItem;
import cn.shop.product.model.Product;
import cn.shop.utils.PageBean;

/*
 * ������service
 */
@Transactional
public class OrderService {
	//ע��dao
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	
	//ҵ��㱣�涩��
	public void save(Order order) {
		orderDao.save(order);
	}

	//ҵ����ѯ����
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean=new PageBean<Order>();
		//����ÿҳ��Ʒ����
		int limit=5;
		pageBean.setLimit(limit);
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//������Ʒ����
		int totalCount=0;
		totalCount=orderDao.findCountByUid(uid);
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
		List<Order> list=orderDao.findByUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
			
	}

	//ҵ�����ݶ���id��ѯ����
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}

	//�޸Ķ�����Ϣ
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}

	//��ҳ��ѯ����
	public PageBean<Order> findAllByPage(Integer page) {
		PageBean<Order> pageBean=new PageBean<Order>();
		//����ÿҳ��Ʒ����
		int limit=10;
		pageBean.setLimit(limit);
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//������Ʒ����
		int totalCount=0;
		totalCount=orderDao.findCount();
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
		List<Order> list=orderDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//��ѯ������
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}
	
}
