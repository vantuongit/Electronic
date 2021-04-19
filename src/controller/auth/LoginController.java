package controller.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.StringUtils;

import constants.GlobalConstant;
import daos.UserDAO;
import model.Users;
import utils.AuthUtil;
import utils.StringUtil;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/auth/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO userDAO = new UserDAO();
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = StringUtil.md5(request.getParameter("password"));
		
		
		Users user = userDAO.findByUserAndPassword(username, password);
		if(user != null) {
			//đúng
			session.setAttribute("userInfo", user);
			response.sendRedirect(request.getContextPath() + "/admin");
			return;
		}else {
			response.sendRedirect(request.getContextPath() + "/auth/login?msg="+GlobalConstant.ERROR);
			return;
		}
		
	}

}
