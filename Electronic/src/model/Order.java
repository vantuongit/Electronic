package model;

import java.util.List;

public class Order{
	private int id;
	private Member member;
	private List<Item> item;
	private Boolean status;

	
	
	
	public Order(int id, Member member) {
		super();
		this.id = id;
		this.member = member;
	}

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(int id, Member member, List<Item> item, Boolean status) {
		super();
		this.id = id;
		this.member = member;
		this.item = item;
		this.status = status;
	}


	public Order(int id, Member member, List<Item> item) {
		super();
		this.id = id;
		this.member = member;
		this.item = item;
	}

	

	public Order(int id, List<Item> item) {
		super();
		this.id = id;
		this.item = item;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public List<Item> getItem() {
		return item;
	}
	public void setItem(List<Item> item) {
		this.item = item;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
	

	
	
	
	
}
