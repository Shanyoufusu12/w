package cn.shop.order.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.shop.cart.model.Cart;
import cn.shop.cart.model.CartItem;
import cn.shop.order.model.Order;
import cn.shop.order.model.OrderItem;
import cn.shop.order.service.OrderService;
import cn.shop.user.model.User;
import cn.shop.utils.PageBean;
import cn.shop.utils.PaymentUtil;

/*
 * 订单action
 */

public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	//注入订单
	private Order order=new Order();
	//接收支付通道编码
	private String pd_FrpId;
	
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	//接受支付成功后的数据
	private String r6_Order;//用户编号
	private String r3_Amt;//支付金额
	
	
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	//注入service
	private OrderService orderService;
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	//接收page
	private Integer page;
	
	public void setPage(Integer page) {
		this.page = page;
	}

	@Override
	public Order getModel() {
		return order;
	}
	
	//跳转到订单页面
	public String save(){
		//将购物车数据保存到订单中
		//订单状态：1，订单提交；2，订单未发货；3，订单未签收；4，订单完成
		order.setState(1);
		//订单提交时间
		order.setOrdertime(new Date());
		//设置用户
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		//验证是否登陆
		if(user==null){
			this.addActionError("请先登录！");
			return "login";
		}
		order.setUser(user);
		order.setName(user.getName());
		order.setAddr(user.getAddr());
		order.setPhone(user.getTell());
		//从session中取出cart
		Cart cart=(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		//设置总计
		order.setTotal(cart.getTotal());
		//验证是否购物
		if(cart == null){
			this.addActionError("购物车是空的，请先购物！");
			return "msg";
		}
		//设置订单项
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem=new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);
		}

		
		orderService.save(order);
		ServletActionContext.getRequest().getSession().removeAttribute("cart");
		return "saveSuccess";
	}
	
	//根据用户id查询订单
	public String findByUid(){
		//从session中取出user
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		//根据page和uid查询订单
		PageBean<Order> pageBean=orderService.findByPageUid(user.getUid(),page);
		//将pagebean存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUid";
	}
	//根据订单id查询订单
	public String findByOid(){
		order=orderService.findByOid(order.getOid());
		return "findByOid";
	}
	//在线支付
	public String payOrder() throws IOException{
		//修改订单
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);
		//为订单付款
		String p0_Cmd="Buy";
		String p1_MerId="10001126856";
		String p2_Order=order.getOid().toString();
		String p3_Amt="0.01";
		String p4_Cur="CNY";
		String p5_Pid="";
		String p6_Pcat="";
		String p7_Pdesc="";
		String p8_Url="http://localhost:8080/ssh/order_callBack.action";
		String p9_SAF="";
		String pa_MP="";
		String pd_FrpId=this.pd_FrpId;
		String pr_NeedResponse="1";
		String keyValue="69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac=PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		
		//向易宝出发
		StringBuffer stringBuffer= new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		stringBuffer.append("p0_Cmd=").append(p0_Cmd).append("&");
		stringBuffer.append("p1_MerId=").append(p1_MerId).append("&");
		stringBuffer.append("p2_Order=").append(p2_Order).append("&");
		stringBuffer.append("p3_Amt=").append(p3_Amt).append("&");
		stringBuffer.append("p4_Cur=").append(p4_Cur).append("&");
		stringBuffer.append("p5_Pid=").append(p5_Pid).append("&");
		stringBuffer.append("p6_Pcat=").append(p6_Pcat).append("&");
		stringBuffer.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		stringBuffer.append("p8_Url=").append(p8_Url).append("&");
		stringBuffer.append("p9_SAF=").append(p9_SAF).append("&");
		stringBuffer.append("pa_MP=").append(pa_MP).append("&");
		stringBuffer.append("pd_FrpId=").append(pd_FrpId).append("&");
		stringBuffer.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		stringBuffer.append("hmac=").append(hmac);
		ServletActionContext.getResponse().sendRedirect(stringBuffer.toString());
		
		return NONE;
	}
	//支付成功显示
	public String callBack(){
		//根据oid查询订单
		Order currOrder=orderService.findByOid(Integer.parseInt(r6_Order));
		//更改订单状态
		currOrder.setState(2);
		orderService.update(currOrder);
		this.addActionMessage("支付成功！订单号："+r6_Order+"支付金额："+r3_Amt);
		return "msg";
	}
	//修改订单状态
	public String updateState(){
		// 根据oid查询订单
		order = orderService.findByOid(order.getOid());
		// 修改订单状态
		order.setState(4);
		// 保存订单
		orderService.update(order);
		return "updateState";
	}
}
