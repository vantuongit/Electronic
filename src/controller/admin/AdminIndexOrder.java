package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CategoryDAO;
import daos.OrderDAO;
import model.Category;
import model.Order;
import utils.AuthUtil;
import utils.DefineUtil;

public class AdminIndexOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexOrder() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// CHECK LOGIN
//				if(!AuthUtil.checkLogin(request,response)) {
//					response.sendRedirect(request.getContextPath()+"/auth/login");
//					return;
//				}

				response.setCharacterEncoding("UTF-8");
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html");
		
		//lấy số tin
		OrderDAO orderDAO = new OrderDAO();
//		int numberOfItem = orderDAO.numberOfItem(); //get number item order
//		int numberOfPage = (int)Math.ceil((float)numberOfItem/DefineUtil.NUMBER_PER_PAGE); // get số trang
//		int currentPage = 1;
//		try {
//			currentPage = Integer.parseInt(request.getParameter("page"));
//		} catch (NumberFormatException e) {
//		}
//		
		List<Order> listOrder = orderDAO.getAll();
		
		
//		request.setAttribute("numberOfItem", numberOfItem);
//		request.setAttribute("numberOfPage", numberOfPage);
//		request.setAttribute("currentPage", currentPage);
		request.setAttribute("listOrder", listOrder);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/order/index.jsp");
		rd.forward(request, response);
	}

}
