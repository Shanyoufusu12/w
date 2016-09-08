package cn.shop.order.adminorderaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.shop.order.model.Order;
import cn.shop.order.model.OrderItem;
import cn.shop.order.service.OrderService;
import cn.shop.utils.PageBean;

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {

	//����page
	private Integer page;
	
	public void setPage(Integer page) {
		this.page = page;
	}
	//ע��order
	private Order order=new Order();
	@Override
	public Order getModel() {
		return order;
	}
	//ע��orderservice
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	//��ѯ����
	public String findAllByPage(){
		//��ҳ��ѯ����
		PageBean<Order> pageBean=orderService.findAllByPage(page);
		//�����ݴ���ֵջ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}
	//��ѯ��������
	public String findOrderItem(){
		//��ѯ������
		List<OrderItem> list=orderService.findOrderItem(order.getOid());
		//�����ݴ���ֵջ
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	//�޸Ķ���״̬
	public String updateState(){
		//����oid��ѯ����
		order=orderService.findByOid(order.getOid());
		//�޸Ķ���״̬
		order.setState(3);
		//���涩��
		orderService.update(order);
		return "updateState";
	}
}
