package model;

public class Producer {
	private int id;
	private String name;
	
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
	public Producer(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Producer() {
		super();
	}
	@Override
	public String toString() {
		return "Producer [id=" + id + ", name=" + name + "]";
	}
	public Producer(String name) {
		super();
		this.name = name;
	}
	public Producer(int id) {
		super();
		this.id = id;
	}
}
