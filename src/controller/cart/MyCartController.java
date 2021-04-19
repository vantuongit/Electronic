package controller.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.Item;
import model.Product;

public class MyCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyCartController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/mycart.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("idProduct"));
		String chk = request.getParameter("achk");
		HttpSession session = request.getSession();
		// List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
		List<Item> listItem = new ArrayList<Item>();
		if (session.getAttribute("listItem") != null) {
			listItem = (List<Item>) session.getAttribute("listItem");
			for (Item item : listItem) {
				if (item.getProduct().getId() == id) {
					if ("reduc".equals(chk)) {
						item.setQty(item.getQty() - 1);
					} else {
						item.setQty(item.getQty() + 1);
					}
					out.print(item.getQty());
					session.setAttribute("listItem", listItem);
					
				
				}
			}
		}
	}
}

