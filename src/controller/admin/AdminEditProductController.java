package controller.admin;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mysql.fabric.xmlrpc.base.Data;

import daos.CategoryDAO;
import daos.ProducerDAO;
import daos.ProductDAO;
import model.Category;
import model.Producer;
import model.Product;
import utils.FileUtil;

@MultipartConfig
public class AdminEditProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private static CategoryDAO categoryDAO;
       private static ProducerDAO producerDAO;
       private static ProductDAO ProductDAO;
    public AdminEditProductController() {
        super();
        categoryDAO = new CategoryDAO();
        producerDAO = new ProducerDAO();
        ProductDAO = new ProductDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id11: "+id);
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/product/index");
			return;
		}
		
		Product product = ProductDAO.getItem(id);
		if(product == null) {
			response.sendRedirect(request.getContextPath() + "/admin/product/index?err=3");
			return;
		}
		
		//get list category
		List<Category> categories = categoryDAO.getCategories();
		//get list producer
		List<Producer> producer = producerDAO.getProducer();
		request.setAttribute("product", product);
		request.setAttribute("producer", producer);
		request.setAttribute("categories", categories);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/product/edit.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		//get data
		int cat_id = 0;
		int producer_id = 0;
		float price = 0;
		int old_price = 0;
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/product/index");
			return;
		}
		
		try {
			cat_id = Integer.parseInt(request.getParameter("category"));
			System.out.println("cat-id: "+cat_id);
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/product/edit");
			return;
		}
		try {
			producer_id = Integer.parseInt(request.getParameter("producer"));
			System.out.println("producer_id: "+producer_id);
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/product/edit");
			return;
		}
		try {
			price = Float.parseFloat(request.getParameter("price"));
			System.out.println("price: "+price);
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/product/edit");
			return;
		}
		try {
			old_price = Integer.parseInt(request.getParameter("old_price"));
			System.out.println("old_price123: "+old_price);
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/product/edit");
			return;
		}
		String name = request.getParameter("name");
		String detail = request.getParameter("detail");
		
		Part filePart = request.getPart("picture");
		//có thể có hoặc k có datecreate, nếu datecreate null thì csdl tự động lấy time hiện tại
		Timestamp date_create = new Timestamp(new Date().getTime());
		//LÀM VALIDATE ĐẦU VÀO
		
		
		//get dữ liệu cũ, ktra có tồn tại trong database
		Product product = ProductDAO.getItem(id);
		if(product == null) {
			response.sendRedirect(request.getContextPath() + "/admin/product/index");
			return;
		}
		
		//tạo thư mục lưu ảnh
		final String dirPathName = request.getServletContext().getRealPath("/uploads");
		File dirFile = new File(dirPathName);
		if(!dirFile.exists()) {
			dirFile.mkdirs();
		}
		//lấy tên file từ part
		String fileName = FileUtil.getName(filePart);
		//đổi tên file
		String picture = "";
		if(fileName.isEmpty()) {
			picture = product.getPicture();
		}else {
			picture = FileUtil.rename(fileName);
		}
		//đường dẫn file
		String filePathName = dirPathName + File.separator + picture;
		Category category = new Category(cat_id, null);
		Producer producers = new Producer(producer_id, null);
		Product item = new Product(id, name, price, old_price, picture, null, 0, detail, category, producers);
		
		ProductDAO productDAO = new ProductDAO();
		if(productDAO.editItem(item) > 0) {
			//thêm thành công
			
			//ghi file
			if(!fileName.isEmpty()) {
				//xóa file cũ
				String oldFilePathName = dirPathName + File.separator + product.getPicture();
				File oldFile = new File(oldFilePathName);
				if(oldFile.exists()) {
					oldFile.delete();
				}
				// ghi file
				filePart.write(filePathName);
			}
			//chuyển trang
			System.out.println("Link save picture: " +dirPathName);
			response.sendRedirect(request.getContextPath() + "/admin/product/index?msg=2");
			return;
		}else {
			//thêm thất bại
			
			//get list category
			List<Category> categories = categoryDAO.getCategories();
			//get list producer
			//producerDAO producerDAO = new producerDAO();
			List<Producer> producer = producerDAO.getProducer();
			request.setAttribute("categories", categories);
			request.setAttribute("producer", producer);
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/product/edit.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
