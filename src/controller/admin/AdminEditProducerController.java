package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.GlobalConstant;
import daos.CategoryDAO;
import daos.ProducerDAO;
import model.Category;
import model.Producer;
import utils.AuthUtil;

public class AdminEditProducerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Category cat;
       
    public AdminEditProducerController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CHECK LOGIN
		if(!AuthUtil.checkLogin(request,response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		ProducerDAO producerDAO = new ProducerDAO();
		
		StringBuilder sbd = new StringBuilder();
		String url = "";
		int id = Integer.parseInt(request.getParameter("id"));
		Producer producer = producerDAO.getById(id);
		if(producer != null) {
			request.setAttribute("producer", producer);
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/producer/edit.jsp");
			rd.forward(request, response);
	
		}
		
		url = sbd.append(request.getContextPath())
				.append("/admin/producer/index")
				.append(GlobalConstant.SUCCESS).toString();
		response.sendRedirect(url);
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
		
		ProducerDAO producerDAO= new ProducerDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Producer producer = new Producer(id, name);
		int countRecordUpdated = producerDAO.update(producer);
		StringBuilder sbd = new StringBuilder();
		String url = "";
		if(countRecordUpdated > 0) {
			url = sbd.append(request.getContextPath())
					.append("/admin/producer/index?msg=")
					.append(GlobalConstant.SUCCESS).toString();
			response.sendRedirect(url);
			return;
		}
		response.sendRedirect(request.getContextPath()+"/admin/producer/index?msg="+GlobalConstant.ERROR);
		
	}

}
