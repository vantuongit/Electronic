package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Users;
import utils.ConnectDBUtils;
import utils.DefineUtil;

public class UserDAO {
private Connection conn;
	
	private Statement st;
	
	private PreparedStatement pst;
	
	private ResultSet rs;

	public int numberOfItems() {
		String sql = "SELECT COUNT(*) AS count FROM Users";
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

	public List<Users> findAllByNameOderByNewName(Users userSearch) {
		String sql = "SELECT id, username, password, fullname, num_phone, address FROM Users WHERE 1";
		if(!"".equals(userSearch.getFullname())) {
			sql += " AND fullname LIKE ?";
		}
		List<Users> users = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			if(!"".equals(userSearch.getFullname())) {
				pst.setString(1, "%" +userSearch.getFullname() + "%" );
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				Users user =new Users(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("num_phone"), rs.getString("address"));
				users.add(user);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,rs,conn);
		}
		return users;
	}

	public List<Users> getUsersPagination(int offset) {
		String sql = "SELECT id, username, password, fullname, num_phone, address FROM Users ORDER BY id DESC LIMIT ?,?";
		List<Users> users = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				Users user =new Users(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("num_phone"), rs.getString("address"));
				users.add(user);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,rs,conn);
		}
		return users;
	}

	public boolean hasUsers(String username) {
		String sql = "SELECT id, username, password, fullname, num_phone, address FROM Users WHERE username = ?";
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
				pst.setString(1, username );
			rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,rs,conn);
		}
		return false;
	}

	public int addItem(Users item) {
		String sql = "INSERT INTO users (username, password, fullname, num_phone, address) VALUES(?, ?, ?, ?, ?)";
		int result=0;
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getUsername());
			pst.setString(2, item.getPassword());
			pst.setString(3, item.getFullname());
			pst.setString(4, item.getNum_phone());
			pst.setString(5, item.getAddress());
			result = pst.executeUpdate();
			System.out.println("result: " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,conn);
		}
		return result;
	}

	public Users getById(int id) {
		String sql = "SELECT id,username,password,fullname, num_phone, address FROM users WHERE id = ?";
		Users user = null;
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				user =new Users(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("num_phone"), rs.getString("address"));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return user;
	}

	public int update(Users user) {
		String sql = "UPDATE users SET username = ?, password = ?, fullname=?, num_phone = ?, address = ? WHERE id = ?";
		int result=0;
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFullname());
			pst.setString(4, user.getNum_phone());
			pst.setString(5, user.getAddress());
			pst.setInt(6, user.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,conn);
		}
		return result;
	}

	public Users findByUserAndPassword(String username, String password) {
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
		Users user = null;
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				user =new Users(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("num_phone"), rs.getString("address"));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return user;
	}

	public int del(int id) {
		String sql = "DELETE FROM users WHERE id = ?";
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

}
