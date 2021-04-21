package model;

public class Users {
	private int id;
	private String username;
	private String password;
	private String fullname;
	private String num_phone;
	private String address;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
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
	public Users(int id, String username, String password, String fullname, String num_phone, String address) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.num_phone = num_phone;
		this.address = address;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", fullname=" + fullname
				+ ", num_phone=" + num_phone + ", address=" + address + "]";
	}
	public Users(String fullname) {
		super();
		this.fullname = fullname;
	}
	
	
}
