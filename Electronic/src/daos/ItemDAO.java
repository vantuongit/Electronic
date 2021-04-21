package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Item;
import utils.ConnectDBUtils;

public class ItemDAO {
	private Connection conn;
	
	private Statement st;
	
	private PreparedStatement pst;
	
	private ResultSet rs;

	public void addItem(int id, Item item) {
		String sql = "INSERT INTO showorders(id_orders,name,price,qty) VALUES (?,?,?,?)";
		conn = ConnectDBUtils.getConnection();

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,id);
			pst.setString(2,item.getProduct().getName());
			pst.setFloat(3,item.getProduct().getPrice());
			pst.setFloat(4,item.getQty());
			
		    pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst, conn);
		}		
	}

	
}
