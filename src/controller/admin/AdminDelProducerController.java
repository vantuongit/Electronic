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
import daos.ProducerDAO;
import model.Category;
import model.Producer;
import utils.AuthUtil;

public class AdminDelProducerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminDelProducerController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CHECK LOGIN
		if(!AuthUtil.checkLogin(request,response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		ProducerDAO producerDAO = new ProducerDAO();
		int countRecordDelete = producerDAO.del(id);
		String url = "";
		StringBuilder sbd = new StringBuilder();
		if(countRecordDelete > 0) {
			url = sbd.append(request.getContextPath())
					.append("/admin/producer/index?msg=")
					.append(GlobalConstant.SUCCESS).toString();
			response.sendRedirect(url);
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
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfo") == null) {
			response.sendRedirect(request.getContextPath()+ "/auth/login");
			return;
		}
		
		ProducerDAO producerDAO = new ProducerDAO();
		String name = request.getParameter("name");
		Producer producer = new Producer(name);
		int countRecordInserted = producerDAO.add(producer);
		StringBuilder sbd = new StringBuilder();
		String url = "";
		if(countRecordInserted > 0) {
			url = sbd.append(request.getContextPath())
					.append("/admin/producer/index?msg=")
					.append(GlobalConstant.SUCCESS).toString();
			response.sendRedirect(url);
			return;
		}
//		//fail
		response.sendRedirect(request.getContextPath()+"/admin/producer/index?msg="+GlobalConstant.ERROR);
		
	}

}
