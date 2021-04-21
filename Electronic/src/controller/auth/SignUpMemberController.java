package controller.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.GlobalConstant;
import daos.MemberDAO;
import daos.UserDAO;
import model.Member;
import model.Users;
import utils.StringUtil;

public class SignUpMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static MemberDAO memberDAO;
    public SignUpMemberController() {
        super();
        memberDAO = new MemberDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/member/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		password = StringUtil.md5(password);
		String fullname = request.getParameter("fullname");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		Member item = new Member(0, username, password, fullname, phone, address);
		//check trùng tên
				if(memberDAO.hasUsers(username)) {
					response.sendRedirect(request.getContextPath()+"/member/signup?msg=1");
					return;
				}
		
		int countRecordInserted = memberDAO.addItem(item);
		System.out.println(countRecordInserted);
		StringBuilder sbd = new StringBuilder();
		String url = "";
		if(countRecordInserted > 0) {
			url = sbd.append(request.getContextPath())
					.append("/member/signup?msg=2")
					.append(GlobalConstant.SUCCESS).toString();
			response.sendRedirect(url);
			return;
		}
		//fail
		response.sendRedirect(request.getContextPath()+"/member/signup?msg="+GlobalConstant.ERROR);
	}
	

}
