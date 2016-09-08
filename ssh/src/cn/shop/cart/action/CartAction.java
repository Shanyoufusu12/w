package cn.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.shop.cart.model.Cart;
import cn.shop.cart.model.CartItem;
import cn.shop.product.model.Product;
import cn.shop.product.service.ProductService;
/*
 * ���ﳵAction
 * 
 */
public class CartAction extends ActionSupport {

	//��ȡpid
	private Integer pid;
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	//��ȡcount
	private Integer count;
	
	public void setCount(Integer count) {
		this.count = count;
	}

	//ע��productservice
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	//��ӵ����ﳵ�ķ���
	public String addCart(){
		//�����ݷ�װ�����������
		CartItem cartItem=new CartItem();
		cartItem.setCount(count);
		Product product=productService.findByPid(pid);
		cartItem.setProduct(product);
		//����������뵽���ﳵ
		Cart cart=getCart();
		cart.addCart(cartItem);
		return "addCart";
	}
	//��չ��ﳵ�ķ���
	public String clearCart(){
		//��session�еõ�cart
		Cart cart=getCart();
		//��չ��ﳵ
		cart.clearCart();
		return "clearCart";
	}

	//ɾ��������ķ���
	public String removeCart(){
		//��session�л��cart
		Cart cart=getCart();
		//��cart���Ƴ�������
		cart.removeCart(pid);
		return "removeCart";
	}
	//�ҵĹ��ﳵִ�з���
	public String myCart(){
		return "myCart";
	}
	//���cart����
	private Cart getCart() {
		Cart cart=(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
}
