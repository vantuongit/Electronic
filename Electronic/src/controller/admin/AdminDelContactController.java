package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.GlobalConstant;
import daos.ContactDAO;
import utils.AuthUtil;

public class AdminDelContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDelContactController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CHECK LOGIN
		if(!AuthUtil.checkLogin(request,response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		ContactDAO contactDAO = new ContactDAO();
		int countRecordDelete = contactDAO.del(id);
		String url = "";
		StringBuilder sbd = new StringBuilder();
		if(countRecordDelete > 0) {
			url = sbd.append(request.getContextPath())
					.append("/admin/contact/index?msg=")
					.append(GlobalConstant.SUCCESS).toString();
			response.sendRedirect(url);
			return;
		}
		response.sendRedirect(request.getContextPath()+"/admin/contact/index?msg=error");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
