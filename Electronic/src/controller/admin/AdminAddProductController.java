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
public class AdminAddProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private static CategoryDAO categoryDAO;
       private static ProducerDAO producerDAO;
    public AdminAddProductController() {
        super();
        categoryDAO = new CategoryDAO();
        producerDAO = new ProducerDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		//get list category
		List<Category> categories = categoryDAO.getCategories();
		//get list producer
		List<Producer> producer = producerDAO.getProducer();
		request.setAttribute("producer", producer);
		request.setAttribute("categories", categories);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/product/add.jsp");
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
		try {
			cat_id = Integer.parseInt(request.getParameter("category"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/product/index?err=1");
			return;
		}
		try {
			producer_id = Integer.parseInt(request.getParameter("producer"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/product/index?err=11");
			return;
		}
		try {
			price = Float.parseFloat(request.getParameter("price"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/product/index");
			return;
		}
		try {
			old_price = Integer.parseInt(request.getParameter("old_price"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/product/index");
			return;
		}
		String name = request.getParameter("name");
		String detail = request.getParameter("detail");
		
		Part filePart = request.getPart("picture");
		//c?? th??? c?? ho???c k c?? datecreate, n???u datecreate null th?? csdl t??? ?????ng l???y time hi???n t???i
		Timestamp date_create = new Timestamp(new Date().getTime());
		//L??M VALIDATE ?????U V??O
		
		//t???o th?? m???c l??u ???nh
		final String dirPathName = request.getServletContext().getRealPath("/uploads");
		File dirFile = new File(dirPathName);
		if(!dirFile.exists()) {
			dirFile.mkdirs();
		}
		//l???y t??n file t??? part
		String fileName = FileUtil.getName(filePart);
		//?????i t??n file
		String picture = FileUtil.rename(fileName);
		//???????ng d???n file
		String filePathName = dirPathName + File.separator + picture;
		Category category = new Category(cat_id, null);
		Producer producers = new Producer(producer_id, null);
		Product item = new Product(0, name, price, old_price, picture, date_create, 0, detail, category, producers);
		
		ProductDAO productDAO = new ProductDAO();
		if(productDAO.addItem(item) > 0) {
			//th??m th??nh c??ng
			
			//ghi file
			if(!fileName.isEmpty()) {
				//k r???ng
				filePart.write(filePathName);
			}
			//chuy???n trang
			System.out.println("Link save picture: " +dirPathName);
			response.sendRedirect(request.getContextPath() + "/admin/product/index?msg=1");
			return;
		}else {
			//th??m th???t b???i
			
			//get list category
			List<Category> categories = categoryDAO.getCategories();
			//get list producer
			//producerDAO producerDAO = new producerDAO();
			List<Producer> producer = producerDAO.getProducer();
			request.setAttribute("categories", categories);
			request.setAttribute("producer", producer);
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/product/add.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
