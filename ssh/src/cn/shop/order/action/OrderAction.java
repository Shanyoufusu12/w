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
 * ����action
 */

public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	//ע�붩��
	private Order order=new Order();
	//����֧��ͨ������
	private String pd_FrpId;
	
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	//����֧���ɹ��������
	private String r6_Order;//�û����
	private String r3_Amt;//֧�����
	
	
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	//ע��service
	private OrderService orderService;
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	//����page
	private Integer page;
	
	public void setPage(Integer page) {
		this.page = page;
	}

	@Override
	public Order getModel() {
		return order;
	}
	
	//��ת������ҳ��
	public String save(){
		//�����ﳵ���ݱ��浽������
		//����״̬��1�������ύ��2������δ������3������δǩ�գ�4���������
		order.setState(1);
		//�����ύʱ��
		order.setOrdertime(new Date());
		//�����û�
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		//��֤�Ƿ��½
		if(user==null){
			this.addActionError("���ȵ�¼��");
			return "login";
		}
		order.setUser(user);
		order.setName(user.getName());
		order.setAddr(user.getAddr());
		order.setPhone(user.getTell());
		//��session��ȡ��cart
		Cart cart=(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		//�����ܼ�
		order.setTotal(cart.getTotal());
		//��֤�Ƿ���
		if(cart == null){
			this.addActionError("���ﳵ�ǿյģ����ȹ��");
			return "msg";
		}
		//���ö�����
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
	
	//�����û�id��ѯ����
	public String findByUid(){
		//��session��ȡ��user
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		//����page��uid��ѯ����
		PageBean<Order> pageBean=orderService.findByPageUid(user.getUid(),page);
		//��pagebean����ֵջ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUid";
	}
	//���ݶ���id��ѯ����
	public String findByOid(){
		order=orderService.findByOid(order.getOid());
		return "findByOid";
	}
	//����֧��
	public String payOrder() throws IOException{
		//�޸Ķ���
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);
		//Ϊ��������
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
		
		//���ױ�����
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
	//֧���ɹ���ʾ
	public String callBack(){
		//����oid��ѯ����
		Order currOrder=orderService.findByOid(Integer.parseInt(r6_Order));
		//���Ķ���״̬
		currOrder.setState(2);
		orderService.update(currOrder);
		this.addActionMessage("֧���ɹ��������ţ�"+r6_Order+"֧����"+r3_Amt);
		return "msg";
	}
	//�޸Ķ���״̬
	public String updateState(){
		// ����oid��ѯ����
		order = orderService.findByOid(order.getOid());
		// �޸Ķ���״̬
		order.setState(4);
		// ���涩��
		orderService.update(order);
		return "updateState";
	}
}
