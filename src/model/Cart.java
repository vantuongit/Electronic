package model;

public class Cart {
	private int id;
	private int pro_id;
	private int qty;
	private int total;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPro_id() {
		return pro_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Cart(int id, int pro_id, int qty, int total) {
		super();
		this.id = id;
		this.pro_id = pro_id;
		this.qty = qty;
		this.total = total;
	}
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
