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

	//接收page
	private Integer page;
	
	public void setPage(Integer page) {
		this.page = page;
	}
	//注入order
	private Order order=new Order();
	@Override
	public Order getModel() {
		return order;
	}
	//注入orderservice
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	//查询订单
	public String findAllByPage(){
		//分页查询订单
		PageBean<Order> pageBean=orderService.findAllByPage(page);
		//将数据存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}
	//查询订单详情
	public String findOrderItem(){
		//查询订单项
		List<OrderItem> list=orderService.findOrderItem(order.getOid());
		//将数据存入值栈
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	//修改订单状态
	public String updateState(){
		//根据oid查询订单
		order=orderService.findByOid(order.getOid());
		//修改订单状态
		order.setState(3);
		//保存订单
		orderService.update(order);
		return "updateState";
	}
}
