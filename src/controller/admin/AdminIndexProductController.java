package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ProductDAO;
import model.Product;
import utils.AuthUtil;
import utils.DefineUtil;

public class AdminIndexProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminIndexProductController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CHECK LOGIN
				if(!AuthUtil.checkLogin(request,response)) {
					response.sendRedirect(request.getContextPath()+"/auth/login");
					return;
				}
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		//get number of songs
				ProductDAO productDAO = new ProductDAO();
				int numberOfItems = productDAO.numberOfItemProduct();  //số bài viết
				int numberOfPage =(int) Math.ceil((float)numberOfItems/DefineUtil.NUMBER_PER_PAGE) ; //số trang
				
				int currentPage = 1;
				try {
					currentPage = Integer.parseInt(request.getParameter("page")); //trang hiện tại
				} catch (NumberFormatException e) {
				}
				
				int offset = (currentPage - 1)*DefineUtil.NUMBER_PER_PAGE; 
				//nếu page ng dùng truyền vào lớn hơn hoặc nhỏ hơn numberOfPage
				if(currentPage > numberOfPage || currentPage < 1 ) {
					currentPage = 1;	
				}
				
				String search="";
				if(request.getParameter("search") != null) {
					//ng dùng có nhập tìm kiếm
					search = request.getParameter("search");
				}
				Product productSearch = new Product(search);
			//	List<Product> listSearch = productDAO.findAllByNameOderByNewName(productSearch);
				
				List<Product> products = productDAO.getAllPagination(offset);
				//if(!"".equals(search)) {
			//		products = listSearch;
			//	}
				
				
				request.setAttribute("numberOfItems", numberOfItems);
				request.setAttribute("numberOfPage", numberOfPage);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("products", products);
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/product/index.jsp");
				rd.forward(request, response);
		
//		ProductDAO productDAO = new ProductDAO();
//		List<Product> products = productDAO.getItems();
//		request.setAttribute("products", products);
//		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/product/index.jsp");
//		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
