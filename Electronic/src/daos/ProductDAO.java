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
import model.Product;
import utils.ConnectDBUtils;
import utils.DefineUtil;

public class ProductDAO {
	private Connection conn;
	
	private Statement st;
	
	private PreparedStatement pst;
	
	private ResultSet rs;

	public List<Product> getItems() {
		List<Product> items = new ArrayList<Product>();
		conn = ConnectDBUtils.getConnection();
		String sql = "SELECT p.id AS pid, p.name AS pname, price, old_price, picture, date_create, counter,"
				+ " detail,producer_id, cat_id, c.name AS cname, producer_name FROM products AS p INNER JOIN"
				+ " categories AS c ON p.cat_id = c.id INNER JOIN producer AS pr ON p.producer_id = pr.id order BY p.id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Product products =new Product(rs.getInt("pid"), rs.getString("pname"), rs.getFloat("price"), rs.getInt("old_price"), rs.getString("picture"), rs.getTimestamp("date_create"), rs.getInt("counter"), rs.getString("detail"), new Category(rs.getInt("cat_id"),rs.getString("cname")), new Producer(rs.getInt("producer_id"),rs.getString("producer_name")));
				items.add(products);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return items;
	}
	//
	public List<Product> getProductSpecial() {
		List<Product> items = new ArrayList<Product>();
		conn = ConnectDBUtils.getConnection();
		String sql = "SELECT p.id AS pid, p.name AS pname, price, old_price, picture, date_create, counter,"
				+ " detail,producer_id, cat_id, c.name AS cname, producer_name FROM products AS p INNER JOIN"
				+ " categories AS c ON p.cat_id = c.id INNER JOIN producer AS pr ON p.producer_id = pr.id  order BY counter DESC LIMIT 2";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Product products =new Product(rs.getInt("pid"), rs.getString("pname"), rs.getFloat("price"), rs.getInt("old_price"), rs.getString("picture"), rs.getTimestamp("date_create"), rs.getInt("counter"), rs.getString("detail"), new Category(rs.getInt("cat_id"),rs.getString("cname")), new Producer(rs.getInt("producer_id"),rs.getString("producer_name")));
				items.add(products);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return items;
	}
	
	public List<Product> getProductNew() {
		List<Product> items = new ArrayList<Product>();
		conn = ConnectDBUtils.getConnection();
		String sql = "SELECT p.id AS pid, p.name AS pname, price, old_price, picture, date_create, counter,"
				+ " detail,producer_id, cat_id, c.name AS cname, producer_name FROM products AS p INNER JOIN"
				+ " categories AS c ON p.cat_id = c.id INNER JOIN producer AS pr ON p.producer_id = pr.id order BY p.id DESC LIMIT 1";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Product products =new Product(rs.getInt("pid"), rs.getString("pname"), rs.getFloat("price"), rs.getInt("old_price"), rs.getString("picture"), rs.getTimestamp("date_create"), rs.getInt("counter"), rs.getString("detail"), new Category(rs.getInt("cat_id"),rs.getString("cname")), new Producer(rs.getInt("producer_id"),rs.getString("producer_name")));
				items.add(products);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return items;
	}

	public int addItem(Product item) {
		int result = 0;
		conn = ConnectDBUtils.getConnection();
		String sql = "INSERT INTO products (name, price, old_price, picture, date_create, counter, detail, producer_id, cat_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, item.getName());
				pst.setFloat(2, item.getPrice());
				pst.setInt(3, item.getOld_price());
				pst.setString(4, item.getPicture());
				pst.setTimestamp(5, item.getDate_create());
				pst.setInt(6, item.getCounter());
				pst.setString(7, item.getDetail());
				pst.setInt(8, item.getProducer().getId());
				pst.setInt(9, item.getCategory().getId());
				result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,conn);
		}
		return result;
		}

	public  Product getItem(int id) {
		Product item = null;
		conn = ConnectDBUtils.getConnection();
		String sql = "SELECT id, name, price, old_price, picture, date_create, counter, detail, producer_id, cat_id FROM products WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				item =new Product(rs.getInt("id"), rs.getString("name"), rs.getFloat("price"), rs.getInt("old_price"), rs.getString("picture"), rs.getTimestamp("date_create"), rs.getInt("counter"), rs.getString("detail"), new Category(rs.getInt("cat_id"),null), new Producer(rs.getInt("producer_id"),null));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,rs,conn);
		}
		return item;
	}

	public int editItem(Product item) {
		int result = 0;
		conn = ConnectDBUtils.getConnection();
		String sql = "UPDATE products  SET name = ?, price = ?, old_price = ?, picture = ?, detail = ?, producer_id = ?, cat_id = ? WHERE id = ?";
		try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, item.getName());
				pst.setFloat(2, item.getPrice());
				pst.setInt(3, item.getOld_price());
				pst.setString(4, item.getPicture());
				pst.setString(5, item.getDetail());
				pst.setInt(6, item.getProducer().getId());
				pst.setInt(7, item.getCategory().getId());
				pst.setInt(8, item.getId());
				result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,conn);
		}
		return result;
	}

	public int delItem(int id) {
		int result = 0;
		conn = ConnectDBUtils.getConnection();
		String sql = "DELETE FROM products WHERE id = ?";
		try {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, id);
				result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,conn);
		}
		return result;
	}


	public int numberOfItemProduct() {
		String sql = "SELECT COUNT(*) AS count FROM products";
		conn = ConnectDBUtils.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
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

	public List<Product> findAllByNameOderByNewName(Product productSearch) {
		String sql = "SELECT p.id AS pid, p.name AS pname, price, old_price, picture, date_create, counter,"
				+ " detail,producer_id, cat_id, c.name AS cname, producer_name FROM products AS p INNER JOIN"
				+ " categories AS c ON p.cat_id = c.id INNER JOIN producer AS pr ON p.producer_id = pr.id  WHERE 1 ";
				if(!"".equals(productSearch.getName())) {
					sql += " AND p.name LIKE ?";
				}
		List<Product> products = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			if(!"".equals(productSearch.getName())) {
				pst.setString(1,"%"+productSearch.getName() +"%");
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				Product product =new Product(rs.getInt("pid"),
						rs.getString("pname"),
						rs.getFloat("price"),
						rs.getInt("old_price"),
						rs.getString("picture"),
						rs.getTimestamp("date_create"),
						rs.getInt("counter"),
						rs.getString("detail"),
						new Category(rs.getInt("cat_id"), rs.getString("cname")),
						new Producer(rs.getInt("producer_id"), rs.getString("producer_name")));
						
				products.add(product);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,rs,conn);
		}
		return products;
	}

	public List<Product> getAllPagination(int offset) {
		String sql = "SELECT p.id AS pid, p.name AS pname, price, old_price, picture, date_create, counter,"
				+ " detail,producer_id, cat_id, c.name AS cname, producer_name FROM products AS p INNER JOIN"
				+ " categories AS c ON p.cat_id = c.id INNER JOIN producer AS pr ON p.producer_id = pr.id order BY p.id DESC LIMIT ?,? ";
		List<Product> products = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				Product product =new Product(rs.getInt("pid"),
						rs.getString("pname"),
						rs.getFloat("price"),
						rs.getInt("old_price"),
						rs.getString("picture"),
						rs.getTimestamp("date_create"),
						rs.getInt("counter"),
						rs.getString("detail"),
						new Category(rs.getInt("cat_id"), rs.getString("cname")),
						new Producer(rs.getInt("producer_id"), rs.getString("producer_name")));
				products.add(product);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,rs,conn);
		}
		return products;
	}
	
	//
	public List<Product> getHeader(int offset) {
		String sql = "SELECT p.id AS pid, p.name AS pname, price, old_price, picture, date_create, counter,"
				+ " detail,producer_id, cat_id, c.name AS cname, producer_name FROM products AS p INNER JOIN"
				+ " categories AS c ON p.cat_id = c.id INNER JOIN producer AS pr ON p.producer_id = pr.id order BY p.id DESC LIMIT ?,? ";
		List<Product> products = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				Product product =new Product(rs.getInt("pid"),
						rs.getString("pname"),
						rs.getFloat("price"),
						rs.getInt("old_price"),
						rs.getString("picture"),
						rs.getTimestamp("date_create"),
						rs.getInt("counter"),
						rs.getString("detail"),
						new Category(rs.getInt("cat_id"), rs.getString("cname")),
						new Producer(rs.getInt("producer_id"), rs.getString("producer_name")));
				products.add(product);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,rs,conn);
		}
		return products;
	}

	public Product getProductDetail(int id) {
		String sql = "SELECT * FROM products WHERE id = ?";
		Product products = null;
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				 products =new Product(rs.getInt("id"),
						rs.getString("name"),
						rs.getFloat("price"),
						rs.getInt("old_price"),
						rs.getString("picture"),
						rs.getTimestamp("date_create"),
						rs.getInt("counter"),
						rs.getString("detail"),
						new Category(rs.getInt("cat_id"), rs.getString("name")),
						new Producer(rs.getInt("producer_id"), rs.getString("name")));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return products;
	}

	public void counterView(int id) {
		String sql = "UPDATE products SET counter = counter + 1 WHERE id = ?";
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,conn);
		}		
	}

	public List<Product> getRelatedSong(Product product, int number) {
		List<Product> products = new ArrayList<>();
		String sql = "SELECT p.id AS pid, p.name AS pname, price, old_price, picture, date_create, counter,"
				+ " detail,producer_id, cat_id, c.name AS cname, producer_name FROM products AS p INNER JOIN"
				+ " categories AS c ON p.cat_id = c.id INNER JOIN producer AS pr ON producer_id = pr.id "
				+ "WHERE cat_id = ? && p.id != ? order BY p.id DESC LIMIT ?";
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, product.getCategory().getId());
			pst.setInt(2, product.getId());
			pst.setInt(3, number);
			rs = pst.executeQuery();
		
			while (rs.next()) {
				Product productss =new Product(rs.getInt("pid"),
							rs.getString("pname"),
							rs.getFloat("price"),
							rs.getInt("old_price"),
							rs.getString("picture"),
							rs.getTimestamp("date_create"),
							rs.getInt("counter"),
							rs.getString("detail"),
							new Category(rs.getInt("cat_id"), rs.getString("cname")),
							new Producer(rs.getInt("producer_id"), rs.getString("producer_name")));
				products.add(productss);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return products;
	}

	public int numberOfItem(int catId) {
		String sql = "SELECT COUNT(*) AS count FROM products WHERE cat_id = ?";
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, catId);
			rs = pst.executeQuery();
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

	public List<Product> getAllByCategoryPagination(int offset, int cat_id) {
		String sql = "SELECT p.id AS pid, p.name AS pname, price, old_price, picture, date_create, counter,"
				+ " detail,producer_id, cat_id, c.name AS cname, producer_name FROM products AS p INNER JOIN"
				+ " categories AS c ON p.cat_id = c.id INNER JOIN producer AS pr ON p.producer_id = pr.id "
				+ "WHERE cat_id = ? order BY p.id DESC LIMIT ?, ?";
//		String sql = "SELECT s.*, c.name AS catName FROM songs AS s "
//				+ "INNER JOIN categories AS c ON s.cat_id = c.id "
//				+ "WHERE cat_id = ? ORDER BY s.id DESC LIMIT ?,?  ";
		List<Product> products = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cat_id);
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				Product product =new Product(rs.getInt("pid"),
						rs.getString("pname"),
						rs.getFloat("price"),
						rs.getInt("old_price"),
						rs.getString("picture"),
						rs.getTimestamp("date_create"),
						rs.getInt("counter"),
						rs.getString("detail"),
						new Category(rs.getInt("cat_id"), rs.getString("cname")),
						new Producer(rs.getInt("producer_id"), rs.getString("producer_name")));
				products.add(product);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,rs,conn);
		}
		return products;
	}


	public List<Product> getAllByProducerPagination(int offset, int producer_id) {
		String sql = "SELECT p.id AS pid, p.name AS pname, price, old_price, picture, date_create, counter,"
				+ " detail,producer_id, cat_id, c.name AS cname, producer_name FROM products AS p INNER JOIN"
				+ " categories AS c ON p.cat_id = c.id INNER JOIN producer AS pr ON p.producer_id = pr.id "
				+ "WHERE producer_id = ? order BY p.id DESC LIMIT ?, ?";
//		String sql = "SELECT s.*, c.name AS catName FROM songs AS s "
//				+ "INNER JOIN categories AS c ON s.cat_id = c.id "
//				+ "WHERE cat_id = ? ORDER BY s.id DESC LIMIT ?,?  ";
		List<Product> products = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, producer_id);
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				Product product =new Product(rs.getInt("pid"),
						rs.getString("pname"),
						rs.getFloat("price"),
						rs.getInt("old_price"),
						rs.getString("picture"),
						rs.getTimestamp("date_create"),
						rs.getInt("counter"),
						rs.getString("detail"),
						new Category(rs.getInt("cat_id"), rs.getString("cname")),
						new Producer(rs.getInt("producer_id"), rs.getString("producer_name")));
				products.add(product);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,rs,conn);
		}
		return products;
	}
	
}
