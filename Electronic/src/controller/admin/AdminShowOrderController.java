package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.GlobalConstant;
import daos.CategoryDAO;
import daos.OrderDAO;
import model.Category;
import model.Item;
import model.Order;
import utils.AuthUtil;

public class AdminShowOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Order order;
       
    public AdminShowOrderController() {
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
		OrderDAO orderDAO = new OrderDAO();
		order = new Order();
		int id = Integer.parseInt(request.getParameter("id"));
		List<Item> listShowOrder = orderDAO.getShowOrder(id);
		request.setAttribute("listShowOrder", listShowOrder);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/order/showOrder.jsp");
		rd.forward(request, response);
		return;
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
