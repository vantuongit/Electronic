 package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CategoryDAO;
import daos.UserDAO;
import model.Category;
import model.Users;
import utils.AuthUtil;
import utils.DefineUtil;

public class AdminIndexUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexUserController() {
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
		
		UserDAO userDAO = new UserDAO();
		int numberOfItems = userDAO.numberOfItems();  //số bài viết
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
		Users userSearch = new Users(search);
		List<Users> listSearch = userDAO.findAllByNameOderByNewName(userSearch);
		
		List<Users> users = userDAO.getUsersPagination(offset);
		if(!"".equals(search)) {
			users = listSearch;
	}
		request.setAttribute("numberOfItems", numberOfItems);
		request.setAttribute("numberOfPage", numberOfPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("users", users);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
