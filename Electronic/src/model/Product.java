package model;

import java.sql.Timestamp;

public class Product {
	private int id;
	private String name;
	private float price;
	private int old_price;
	private String picture;
	private Timestamp date_create;
	private int counter;
	private String detail;
	private Category category;
	private Producer producer;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public int getOld_price() {
		return old_price;
	}
	public void setOld_price(int old_price) {
		this.old_price = old_price;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Timestamp getDate_create() {
		return date_create;
	}
	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Producer getProducer() {
		return producer;
	}
	public void setProducer(Producer producer) {
		this.producer = producer;
	}
	public Product(int id, String name, Float price, int old_price, String picture, Timestamp date_create, int counter,
			String detail, Category category, Producer producer) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.old_price = old_price;
		this.picture = picture;
		this.date_create = date_create;
		this.counter = counter;
		this.detail = detail;
		this.category = category;
		this.producer = producer;
	}
	
	public Product(String name, Float price, int old_price, String picture) {
		super();
		this.name = name;
		this.price = price;
		this.old_price = old_price;
		this.picture = picture;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Product(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Product(String name) {
		super();
		this.name = name;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", old_price=" + old_price + ", picture="
				+ picture + ", date_create=" + date_create + ", counter=" + counter + ", detail=" + detail
				+ ", category=" + category + ", producer=" + producer + "]";
	}
	public Product(int id, String name, Float price, int old_price, String picture) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.old_price = old_price;
		this.picture = picture;
	}
	public Product(int id, String name, String picture) {
		super();
		this.id = id;
		this.name = name;
		this.picture = picture;
	}
	
	
	
}
