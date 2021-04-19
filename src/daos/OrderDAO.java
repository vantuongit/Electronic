package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Contact;
import model.Item;
import model.Member;
import model.Order;
import model.Product;
import utils.ConnectDBUtils;

public class OrderDAO {
	private Connection conn;
	
	private Statement st;
	
	private PreparedStatement pst;
	
	private ResultSet rs;

	public int addItem(Order order) {
		int result = 0;
		conn = ConnectDBUtils.getConnection();
		String sql = "INSERT INTO orders (name, phone, address) VALUES (?, ?, ?)";
		try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, order.getMember().getFullname());
				pst.setString(2, order.getMember().getPhone());
				pst.setString(3, order.getMember().getAddress());
				result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,conn);
		}
		return result;
	}

	public int numberOfItem() {
		String sql = "SELECT COUNT(*) AS count FROM orders";
		conn = ConnectDBUtils.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				int count = rs.getInt("count");
				return count;
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return 0;
	}


	public List<Order> getAll (){
		String sql = "SELECT * FROM orders ORDER BY id DESC";
		List<Order> orders = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Order order =new Order(rs.getInt("id"),new Member(rs.getString("name"),rs.getString("phone"),rs.getString("address")));
				orders.add(order);
			
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return orders;
	}

	public List<Order> getShowOrder (){
		String sql = "SELECT * FROM orders ORDER BY id DESC";
		List<Order> orders = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Order order =new Order(rs.getInt("id"),
						new Member(rs.getString("name"),rs.getString("phone"),rs.getString("address")));
				orders.add(order);
			
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return orders;
	}

	public int addDetailItem(Item item) {
		int result = 0;
		conn = ConnectDBUtils.getConnection();
		String sql = "INSERT INTO showorders (name, price, qty, id_orders) VALUES (?, ?, ?, ?)";
		try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, item.getProduct().getName());
				pst.setFloat(2, item.getPrice());
				pst.setInt(3, item.getQty());
				pst.setInt(4, item.getId_order());
				result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,conn);
		}
		return result;
	}

	public List<Item> getShowOrder(int id) {
		String sql = "SELECT * FROM showorders WHERE id_orders = ?";
		List<Item> orderList = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,id);
			rs = pst.executeQuery();
			while (rs.next()) {

				Item showOrders=new Item(rs.getInt("id_orders"),
						new Product(rs.getString("name")),
						rs.getInt("qty"),
						rs.getFloat("price"));
				orderList.add(showOrders);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return orderList;
	}

	public int addOrder(Order order) {
		int result = 0;
		String sql = "INSERT INTO orders( name,phone,address) VALUES (?,?,?)";
		conn = ConnectDBUtils.getConnection();

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1,order.getMember().getFullname());
			pst.setString(2,order.getMember().getPhone());
			pst.setString(3,order.getMember().getAddress());
			
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st, rs, conn);
		}
		return result;
	
	}

	public int getIDOrder(Order order) {
		String sql = "SELECT id FROM orders WHERE name = ? AND phone = ? ORDER BY id DESC LIMIT 1";
		int id=0;
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1,order.getMember().getFullname());
			pst.setString(2,order.getMember().getPhone());
			rs=pst.executeQuery();
			if(rs.next()) {
				  id=rs.getInt("id");
				
			}
			//result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,conn);
		}
		return id;
	}


}
