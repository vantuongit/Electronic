package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CategoryDAO;
import model.Category;
import utils.AuthUtil;
import utils.DefineUtil;

public class AdminIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminIndexCatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
			}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// CHECK LOGIN
		if(!AuthUtil.checkLogin(request,response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}

		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		//lấy số cat
		CategoryDAO categoryDAO = new CategoryDAO();
		int numberOfItem = categoryDAO.numberOfItem(); //get number item cat 
		int numberOfPage = (int)Math.ceil((float)numberOfItem/DefineUtil.NUMBER_PER_PAGE); // get số trang
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
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
		Category catSearch = new Category(search);
		List<Category> listSearch = categoryDAO.findAllByNameOderByNewName(catSearch);
		List<Category> categories = categoryDAO.getCategoriesPagination(offset);
		
		if(!"".equals(search)) {
		categories = listSearch;
	}
		
		request.setAttribute("numberOfItem", numberOfItem);
		request.setAttribute("numberOfPage", numberOfPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("categories", categories);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/index.jsp");
		rd.forward(request, response);


	}}
