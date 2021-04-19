package controller.publics;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.ItemDAO;
import daos.MemberDAO;
import daos.OrderDAO;
import model.Item;
import model.Member;
import model.Order;
import utils.AuthUtil;

public class CheckOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CheckOutController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		MemberDAO memberDAO = new MemberDAO();
		OrderDAO orderDAO = new OrderDAO();
		ItemDAO itemDAO = new ItemDAO();
		//Member member = new Member();
		HttpSession session = request.getSession();
		Member member =(Member) session.getAttribute("userInfo");
		Item item =(Item) session.getAttribute("item");
		if(member == null) {
			response.sendRedirect(request.getContextPath()+"/login-checkout");
			return;
		}else {
			Order order= (Order)session.getAttribute("order");
			order.setMember(member);
			if(orderDAO.addOrder(order) > 0) {
				int id = orderDAO.getIDOrder(order);
				List<Item> listItem = (List<Item>)session.getAttribute("listItem");
				for(Item items : listItem) {
					itemDAO.addItem(id,items);
					session.removeAttribute("order");
					session.removeAttribute("listItem");
				}
			}
			//session.removeAttribute("order");
			response.sendRedirect(request.getContextPath() + "/my-cart?msg=1");
			return;
		}
	}

}
