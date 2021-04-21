package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Producer;
import utils.ConnectDBUtils;
import utils.DefineUtil;

public class ProducerDAO {

	private Connection conn;
	
	private Statement st;
	
	private PreparedStatement pst;
	
	private ResultSet rs;
	
	public List<Producer> getProducer (){
		String sql = "SELECT id, producer_name FROM producer ORDER BY id DESC";
		List<Producer> producer = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Producer item =new Producer(rs.getInt("id"), rs.getString("producer_name"));
				producer.add(item);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return producer;
	}
	public int numberOfItem() {
		String sql = "SELECT COUNT(*) AS count FROM producer";
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
	public List<Producer> findAllByNameOderByNewName(Producer producerSearch) {
		String sql = "SELECT id, producer_name FROM producer "
				+ "WHERE 1";
		if(!"".equals(producerSearch.getName())) {
			sql += " AND name LIKE ?";
		}
				// "ORDER BY id DESC";
		List<Producer> producer = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			if(!"".equals(producerSearch.getName())) {
			pst.setString(1, "%" + producerSearch.getName() + "%" );
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				Producer producers =new Producer(rs.getInt("id"), rs.getString("producer_name"));
				producer.add(producers);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,rs,conn);
		}
		return producer;
	}
	public List<Producer> getProducerPagination(int offset) {
		String sql = "SELECT id, producer_name FROM producer ORDER BY id DESC LIMIT ?,?";
		List<Producer> producer = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				Producer producers =new Producer(rs.getInt("id"), rs.getString("producer_name"));
				producer.add(producers);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,rs,conn);
		}
		return producer;
	}
	public boolean hasProducer(String name) {
		String sql = "SELECT id, producer_name FROM producer "
				+ "WHERE producer_name = ?";
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
			while (rs.next()) {
				return true;
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,rs,conn);
		}
		return false;
	}
	public int add(Producer producer) {
		String sql = "INSERT INTO producer (producer_name) VALUES(?)";
		int result=0;
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, producer.getName());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,conn);
		}
		return result;
	}
	public Producer getById(int id) {
		String sql = "SELECT id, producer_name FROM producer WHERE id = ?";
		Producer producer = null;
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				producer =new Producer(rs.getInt("id"), rs.getString("producer_name"));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return producer;
	}
	public int update(Producer producer) {
		String sql = "UPDATE producer SET producer_name = ? WHERE id = ?";
		int result=0;
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, producer.getName());
			pst.setInt(2, producer.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,conn);
		}
		return result;
	}
	public int del(int id) {
		String sql = "DELETE FROM producer WHERE id = ?";
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
