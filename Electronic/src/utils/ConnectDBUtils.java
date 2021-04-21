package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDBUtils {

	public static final String URL ="jdbc:mysql://localhost:3306/electronicstore?useUnicode=yes&characterEncoding=UTF-8";
	
	public static final String USERNAME ="root";
	
	public static final String PASSWORD ="";
	
	public static Connection getConnection() {
		
		Connection conn = null;
					try {
						Class.forName("com.mysql.jdbc.Driver");
						conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
						//láº¥y ra danh sÃ¡ch cÃ¡c danh má»¥c
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
					
		return conn;
	}
	
	//overload: cùng tên, khác tham số truyền vào, cùng file
	public static void close(ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} 
	
	public static void close(Statement st) {
		if(st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void close(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(PreparedStatement pst) {
		if(pst!=null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement st, ResultSet rs, Connection conn) {
		close(st);
		close(rs);
		close(conn);
	}
	public static void close(PreparedStatement pst, ResultSet rs, Connection conn) {
		close(pst);
		close(rs);
		close(conn);
	}
	public static void close(PreparedStatement pst, Connection conn) {
		close(pst);
		close(conn);
	}
}
