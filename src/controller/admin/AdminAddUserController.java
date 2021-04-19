package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.CategoryDAO;
import daos.UserDAO;
import model.Category;
import model.Users;
import utils.AuthUtil;
import utils.StringUtil;

public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserDAO userDAO;
       
    public AdminAddUserController() {
        super();
        userDAO = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CHECK LOGIN
		if(!AuthUtil.checkLogin(request,response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		//chỉ admin mới đc thêm người dùng
		HttpSession session = request.getSession();
		Users userInfo = (Users) session.getAttribute("userInfo");
		if(!"admin".equals(userInfo.getUsername())) {
			//không được phép
			response.sendRedirect(request.getContextPath()+ "/admin/user/index?err=1");
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CHECK LOGIN
		if(!AuthUtil.checkLogin(request,response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		//chỉ admin mới đc thêm người dùng
		HttpSession session = request.getSession();
		Users userInfo = (Users) session.getAttribute("userInfo");
		if(!"admin".equals(userInfo.getUsername())) {
			//không được phép
			response.sendRedirect(request.getContextPath()+ "/admin/user/index?err=1");
			return;
		}
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		//Validate dữ liệu
		
		//mã hóa pass bằng phương thức md5
		if(session.getAttribute("userInfo") == null) {
			response.sendRedirect(request.getContextPath()+ "/auth/login");
			return;
		}
		UserDAO userDAO = new UserDAO();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		password = StringUtil.md5(password);
		String fullname = request.getParameter("fullname");
		String num_phone = request.getParameter("num_phone");
		String address = request.getParameter("address");
		Users item = new Users(0, username, password, fullname, num_phone, address);
		//check trùng tên
				if(userDAO.hasUsers(username)) {
					response.sendRedirect(request.getContextPath()+"/admin/user/index?err=5");
					return;
				}
		
		int countRecordInserted = userDAO.addItem(item);
		System.out.println(countRecordInserted);
		StringBuilder sbd = new StringBuilder();
		String url = "";
		if(countRecordInserted > 0) {
			url = sbd.append(request.getContextPath())
					.append("/admin/user/index?msg=")
					.append(GlobalConstant.SUCCESS).toString();
			response.sendRedirect(url);
			return;
		}
		//fail
		response.sendRedirect(request.getContextPath()+"/admin/user/index?msg="+GlobalConstant.ERROR);
	}
}
