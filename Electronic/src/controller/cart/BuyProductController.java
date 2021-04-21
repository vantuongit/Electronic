package controller.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.fabric.Response;

import daos.ProductDAO;
import model.Item;
import model.Order;
import model.Product;

public class BuyProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Product> listProduct = new ArrayList<>();

	public BuyProductController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	PrintWriter out = response.getWriter();
		
		//int total = 0;
		boolean check = false;
		int qty = 1;
		int id = Integer.parseInt(request.getParameter("AidProduct"));
		ProductDAO productDAO = new ProductDAO();
		Product product = productDAO.getItem(id);
		HttpSession session = request.getSession();
		if (session.getAttribute("order") == null) {
			List<Item> listItem = new ArrayList<Item>();
			Order order = new Order();
			Item item = new Item();
			item.setQty(qty);
			item.setId(id);
			item.setProduct(product);
			item.setPrice(product.getPrice());
			listItem.add(item);
			order.setItem(listItem);
			session.setAttribute("order", order);
			session.setAttribute("item", item);
			session.setAttribute("listItem", listItem);
			out.print(listItem.size());

		} else {
			Order order = (Order)session.getAttribute("order");
			List<Item> listItem = order.getItem();
			for (Item item : listItem) {
				if (item.getProduct().getId() == product.getId()) {
					item.setQty(item.getQty() + qty);
					check = true;
					//out.print(String.format("<p>total:%.0f<p/>", total));	
				}
			}
			if (check == false) {
				Item item = new Item();
				item.setQty(qty);
				item.setId(id);
				item.setProduct(product);
				item.setPrice(product.getPrice());
				listItem.add(item);
				
			}
			session.setAttribute("listItem", listItem);
			session.setAttribute("order", order);
			out.print(listItem.size());
		}
		
		
	}

}
