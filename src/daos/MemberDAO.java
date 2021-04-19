package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Member;
import model.Users;
import utils.ConnectDBUtils;

public class MemberDAO {

	private Connection conn;
	
	private Statement st;
	
	private PreparedStatement pst;
	
	private ResultSet rs;

	public boolean hasUsers(String username) {
		String sql = "SELECT id, username, password, fullname, phone, address FROM members WHERE username = ?";
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

	public int addItem(Member item) {
		String sql = "INSERT INTO members (username, password, fullname, phone, address) VALUES(?, ?, ?, ?, ?)";
		int result=0;
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getUsername());
			pst.setString(2, item.getPassword());
			pst.setString(3, item.getFullname());
			pst.setString(4, item.getPhone());
			pst.setString(5, item.getAddress());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,conn);
		}
		return result;
	}

	public Member findByUserAndPassword(String username, String password) {
		String sql = "SELECT * FROM members WHERE username = ? AND password = ?";
		Member member = null;
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				member =new Member(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("phone"), rs.getString("address"));	
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return member;
	}
	
}
