package model;

public class Contact {
	private int id;
	private String name;
	private String email;
	private String num_phone;
	private String address;
	private String message;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNum_phone() {
		return num_phone;
	}
	public void setNum_phone(String num_phone) {
		this.num_phone = num_phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Contact(int id, String name, String email, String num_phone, String address, String message) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.num_phone = num_phone;
		this.address = address;
		this.message = message;
	}
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Contact(String name) {
		super();
		this.name = name;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", email=" + email + ", num_phone=" + num_phone + ", address=" + address
				+ ", message=" + message + "]";
	}
	
}
