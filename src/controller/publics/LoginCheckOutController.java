package controller.publics;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import daos.ItemDAO;
import daos.OrderDAO;
import model.Item;
import model.Member;
import model.Order;

public class LoginCheckOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginCheckOutController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/checkout.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		OrderDAO orderDAO = new OrderDAO();
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		Member member = new Member(name, phone, address);
//		Item item =(Item) Session.getAttribute("item");
		Order order = new Order();
		order.setMember(member);
		if(orderDAO.addItem(order) > 0) {
			int id = orderDAO.getIDOrder(order);
			List<Item> listItem = (List<Item>)session.getAttribute("listItem");
			for(Item items : listItem) {
				ItemDAO itemDAO = new ItemDAO();
				itemDAO.addItem(id,items);
				session.removeAttribute("order");
				session.removeAttribute("listItem");
			}
			response.sendRedirect(request.getContextPath() + "/my-cart?msg=1");
		
	}
	}
}
