package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Item {
	private int id;
	private Product product;
	private int qty;
	private float price;
	private float total;
	private int id_order;
	
	
	public int getId_order() {
		return id_order;
	}

	public void setId_order(int id_order) {
		this.id_order = id_order;
	}

	public int getId() {
		return id;
	}
	
	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Item(int id, Product product, int qty, float price) {
		super();
		this.id = id;
		this.product = product;
		this.qty = qty;
		this.price = price;
	}
	
	public Item() {
		super();
	}

	public Item(int id, Product product, int qty, float total, float price) {
		super();
		this.id = id;
		this.product = product;
		this.qty = qty;
		this.total = total;
		this.price = price;
	}
	public Item(int id, int qty, float total, float price) {
		super();
		this.id = id;
		this.qty = qty;
		this.total = total;
		this.price = price;
	}
	public Item(int id, Product product) {
		super();
		this.id = id;
		this.product = product;
	}


	
}
