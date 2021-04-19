package model;

public class Member {
	private int id;
	private String username;
	private String password;
	private String fullname;
	private String phone;
	private String address;
	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Member(int id, String fullname, String phone, String address) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.phone = phone;
		this.address = address;
	}
	public Member() {
		super();
	}

	public Member(int id, String username, String password, String fullname, String phone, String address) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.phone = phone;
		this.address = address;
	}

	public Member(String fullname, String phone, String address) {
		super();
		this.fullname = fullname;
		this.phone = phone;
		this.address = address;
	}

	public Member(int id, String username, String fullname, String phone, String address) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.phone = phone;
		this.address = address;
	}

	
	
	
}
