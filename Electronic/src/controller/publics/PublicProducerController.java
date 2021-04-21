package controller.publics;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.UrlConstants;
import daos.CategoryDAO;
import daos.ProducerDAO;
import daos.ProductDAO;
import model.Category;
import model.Producer;
import model.Product;
import utils.DefineUtil;

public class PublicProducerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProducerDAO producerDAO;
	private ProductDAO productDAO;
       
    public PublicProducerController() {
        super();
        productDAO = new ProductDAO();
        producerDAO = new ProducerDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		int id = 0;
		int currentPage = 1;
		try {
			 id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			currentPage=1;
		}
		
		Producer producer = producerDAO.getById(id);
		if(producer == null) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		int numberOfItem = productDAO.numberOfItem(id); //số bài
		int numberOfPage = (int)Math.ceil((float)numberOfItem/DefineUtil.NUMBER_PER_PAGE); //số trang
		
		if(currentPage >numberOfPage || currentPage < 1) {
			currentPage =1;
		}
		int offset = (currentPage-1) * DefineUtil.NUMBER_PER_PAGE;
		
		List<Product> products = productDAO.getAllByProducerPagination(offset,id);
		
		request.setAttribute("numberOfItem", numberOfItem);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("numberOfPage", numberOfPage);
		request.setAttribute("producer", producer);
		request.setAttribute("products", products);
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/cat.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
