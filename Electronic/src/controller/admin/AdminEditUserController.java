package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminEditUserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CHECK LOGIN
		if(!AuthUtil.checkLogin(request,response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		UserDAO userDAO = new UserDAO();
		
		StringBuilder sbd = new StringBuilder();
		String url = "";
		int id = Integer.parseInt(request.getParameter("id"));
		//chỉ admin mới đc sửa người dùng
		HttpSession session = request.getSession();
		Users userInfo = (Users) session.getAttribute("userInfo");
		if("admin".equals(userDAO.getById(userInfo.getId()).getUsername()) || (id == userInfo.getId())) {
			Users user = userDAO.getById(id);
			if(user != null) {
				//được sửa
				request.setAttribute("user", user);
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
				rd.forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath() + "/admin/user/index?err=3");
				return;
			}
		
		}else {
			//k có quyền
			response.sendRedirect(request.getContextPath() + "/admin/user/index?err=2");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CHECK LOGIN
		if(!AuthUtil.checkLogin(request,response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		UserDAO userDAO = new UserDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		//chỉ admin mới đc sửa người dùng
				HttpSession session = request.getSession();
				Users userInfo = (Users) session.getAttribute("userInfo");
				if("admin".equals(userDAO.getById(userInfo.getId()).getUsername()) || (id == userInfo.getId())) {
					String username = request.getParameter("username");
					String password = request.getParameter("password");
					String fullname = request.getParameter("fullname");
					String num_phone = request.getParameter("num_phone");
					String address = request.getParameter("address");
					Users user = new Users(id, username, password, fullname, num_phone, address);
					int countRecordUpdated = userDAO.update(user);
					StringBuilder sbd = new StringBuilder();
					String url = "";
					if(countRecordUpdated > 0) {
						url = sbd.append(request.getContextPath())
								.append("/admin/user/index?msg=")
								.append(GlobalConstant.SUCCESS).toString();
						response.sendRedirect(url);
					}
				}else {
					//k có quyền
					response.sendRedirect(request.getContextPath() + "/admin/user/index?err=2");
					return;
				}
	}

}
