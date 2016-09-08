package cn.shop.categorysecond.model;

import java.util.HashSet;
import java.util.Set;

import cn.shop.category.model.Category;
import cn.shop.product.model.Product;

public class CategorySecond {

	private Integer csid;
	private String csname;
	//创建一级分类对象
	private Category category;
	//创建商品集合
	private Set<Product> products=new HashSet<Product>();
	
	
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
