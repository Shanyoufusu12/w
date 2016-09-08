package cn.shop.cart.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * ��װ���ﳵ����
 * 
 */
public class Cart implements Serializable{
	//����
	//�������
	private Map<Integer,CartItem> map=new LinkedHashMap<Integer,CartItem>();
	
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	//�ܼ�
	private double total;
	
	public double getTotal() {
		return total;
	}
	//����
	//��չ��ﳵ
	public void clearCart(){
		//�ܼ�=0
		total=0;
		//��չ������
		map.clear();
	}
	//ɾ��������
	public void removeCart(Integer pid){
		//��������ӹ������ɾ��
		 CartItem cartItem=map.remove(pid);
		//�ܼ�=�ܼ�-С��
		 total-=cartItem.getSubtotal();
	}
	//��ӹ�����
	public void addCart(CartItem cartItem){
		//�ж�ԭ����������Ƿ��иù�����
		Integer pid=cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			//���ڣ���������
			CartItem _cartItem=map.get(pid);
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else{
			//�����ڣ����빺�����
			map.put(pid,cartItem);
		}
		//�ܼ�=�ܼ�+С��
		total+=cartItem.getSubtotal();
	}
}
