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

public class AdminDelUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDelUserController() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CHECK LOGIN
		if(!AuthUtil.checkLogin(request,response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		//chỉ admin mới đc thêm người dùng
				HttpSession session = request.getSession();
				Users userInfo = (Users) session.getAttribute("userInfo");
				//lấy user muốn xóa
				UserDAO userDAO = new UserDAO();
				Users user = userDAO.getById(id);
				if("admin".equals(user.getUsername())) {
					//k đc phép
					response.sendRedirect(request.getContextPath() + "/admin/user/index?err=4");
					return;
				}else {
					if("admin".equals(userInfo.getUsername())) {
						//đc phép xóa
						int countRecordDelete = userDAO.del(id);
						String url = "";
						StringBuilder sbd = new StringBuilder();
						if(countRecordDelete > 0) {
							url = sbd.append(request.getContextPath())
									.append("/admin/user/index?msg=")
									.append(GlobalConstant.SUCCESS).toString();
							response.sendRedirect(url);
							return;
						}else {
							response.sendRedirect(request.getContextPath() + "/admin/user/index?err=4");
							return;
						}
					}else {
						response.sendRedirect(request.getContextPath() + "/admin/user/index?err=4");
						return;
					
					}
				}
				
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}}


