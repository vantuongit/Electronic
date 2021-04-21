package controller.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.MemberDAO;
import daos.UserDAO;
import model.Member;
import model.Users;
import utils.StringUtil;

public class SignInMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignInMemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/member/login.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO memberDAO = new MemberDAO();
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = StringUtil.md5(request.getParameter("password"));
		
		
		Member member = memberDAO.findByUserAndPassword(username, password);
		if(member != null) {
			//đúng
			session.setAttribute("userInfo", member);
			response.sendRedirect(request.getContextPath() + "/home?msg=1");
			return;
		}else {
			response.sendRedirect(request.getContextPath() + "/member/signup?msg="+GlobalConstant.ERROR);
			return;
		}
	}

}
