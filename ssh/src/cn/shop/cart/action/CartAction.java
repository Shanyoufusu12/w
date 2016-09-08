package cn.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.shop.cart.model.Cart;
import cn.shop.cart.model.CartItem;
import cn.shop.product.model.Product;
import cn.shop.product.service.ProductService;
/*
 * 购物车Action
 * 
 */
public class CartAction extends ActionSupport {

	//获取pid
	private Integer pid;
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	//获取count
	private Integer count;
	
	public void setCount(Integer count) {
		this.count = count;
	}

	//注入productservice
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	//添加到购物车的方法
	public String addCart(){
		//将数据封装到购物项对象
		CartItem cartItem=new CartItem();
		cartItem.setCount(count);
		Product product=productService.findByPid(pid);
		cartItem.setProduct(product);
		//将购物项加入到购物车
		Cart cart=getCart();
		cart.addCart(cartItem);
		return "addCart";
	}
	//清空购物车的方法
	public String clearCart(){
		//从session中得到cart
		Cart cart=getCart();
		//清空购物车
		cart.clearCart();
		return "clearCart";
	}

	//删除购物项的方法
	public String removeCart(){
		//从session中获得cart
		Cart cart=getCart();
		//从cart中移除购物项
		cart.removeCart(pid);
		return "removeCart";
	}
	//我的购物车执行方法
	public String myCart(){
		return "myCart";
	}
	//获得cart方法
	private Cart getCart() {
		Cart cart=(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
}
