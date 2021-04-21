 package controller.publics;

 import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.ProductDAO;
import model.Product;
public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
       
    public PublicDetailController() {
        super();
        productDAO = new ProductDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=0;
		try {
			id=Integer.parseInt( request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		Product product = productDAO.getProductDetail(id);
		if(product ==null) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		HttpSession session = request.getSession();
		String hasVisited = (String) session.getAttribute("hasVisited: " + id);
		if(hasVisited == null) {
			session.setAttribute("hasVisited: " + id, "yes");
			session.setMaxInactiveInterval(3600); // thời gian reset session
			productDAO.counterView(id);

		}
		
		List<Product> relatedProduct = productDAO.getRelatedSong(product, 3); // hiển thị 3 tin liên quan
		request.setAttribute("product", product);
		request.setAttribute("relatedProduct", relatedProduct);
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/detail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
