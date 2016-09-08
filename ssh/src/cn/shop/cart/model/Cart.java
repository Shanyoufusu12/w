package cn.shop.cart.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * 封装购物车对象
 * 
 */
public class Cart implements Serializable{
	//属性
	//购物项集合
	private Map<Integer,CartItem> map=new LinkedHashMap<Integer,CartItem>();
	
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	//总计
	private double total;
	
	public double getTotal() {
		return total;
	}
	//方法
	//清空购物车
	public void clearCart(){
		//总计=0
		total=0;
		//清空购物项集合
		map.clear();
	}
	//删除购物项
	public void removeCart(Integer pid){
		//将购物项从购物项集合删除
		 CartItem cartItem=map.remove(pid);
		//总计=总计-小计
		 total-=cartItem.getSubtotal();
	}
	//添加购物项
	public void addCart(CartItem cartItem){
		//判断原购物项集合中是否有该购物项
		Integer pid=cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			//存在：数量增加
			CartItem _cartItem=map.get(pid);
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else{
			//不存在：加入购物项集合
			map.put(pid,cartItem);
		}
		//总计=总计+小计
		total+=cartItem.getSubtotal();
	}
}
