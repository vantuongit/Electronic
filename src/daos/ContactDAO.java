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
import utils.ConnectDBUtils;
import utils.DefineUtil;

public class ContactDAO {
	private Connection conn;
	
	private Statement st;
	
	private PreparedStatement pst;
	
	private ResultSet rs;
	
	public List<Contact> getAll (){
		String sql = "SELECT * FROM contacts ORDER BY id DESC";
		List<Contact> contact = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Contact contacts =new Contact(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("num_phone"), 
						rs.getString("address"),
						rs.getString("message"));
				contact.add(contacts);
			
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return contact;
	}
	
	
	public int del (int id){
		String sql = "DELETE FROM contacts WHERE id = ?";
		int result=0;
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,conn);
		}
		return result;
	}


	public int numberOfItem() {
		String sql = "SELECT COUNT(*) AS count FROM contacts";
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

	public List<Contact> getAllPagination(int offset) {
		String sql = "SELECT * FROM contacts ORDER BY id DESC LIMIT ?,?";
		List<Contact> contact = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				Contact contacts =new Contact(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("num_phone"), 
						rs.getString("address"),
						rs.getString("message"));
				contact.add(contacts);
			
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return contact;
		
	}

	public List<Contact> findAllByNameOderByNewName(Contact contactSearch) {
		String sql = "SELECT * FROM contacts WHERE 1";
		if(!"".equals(contactSearch.getName())) {
			sql += " AND name LIKE ?";
		}
		List<Contact> contact = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + contactSearch.getName() + "%");
			rs = pst.executeQuery();
			while (rs.next()) {
				Contact contacts =new Contact(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("num_phone"), 
						rs.getString("address"),
						rs.getString("message"));
				contact.add(contacts);
			
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return contact;
			}


	public int addItem(Contact item) {
		String sql = "INSERT INTO contacts(name, email, num_phone, address, message) VALUES(?, ?, ?, ?, ?)";
		int result=0;
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getName());
			pst.setString(2, item.getEmail());
			pst.setString(3, item.getNum_phone());
			pst.setString(4, item.getAddress());
			pst.setString(5, item.getMessage());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,conn);
		}
		return result;
	}
}
