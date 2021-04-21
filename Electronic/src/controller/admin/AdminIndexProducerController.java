package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CategoryDAO;
import daos.ProducerDAO;
import model.Category;
import model.Producer;
import utils.AuthUtil;
import utils.DefineUtil;

public class AdminIndexProducerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminIndexProducerController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
			}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		// CHECK LOGIN
		if(!AuthUtil.checkLogin(request,response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}

		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		//lấy số cat
		ProducerDAO producerDAO = new ProducerDAO();
		int numberOfItem = producerDAO.numberOfItem(); //get number item cat 
		int numberOfPage = (int)Math.ceil((float)numberOfItem/DefineUtil.NUMBER_PER_PAGE); // get số trang
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
		}
		int offset = (currentPage - 1)*DefineUtil.NUMBER_PER_PAGE; 
		//nếu page ng dùng truyền vào lớn hơn hoặc nhỏ hơn numberOfPage
				if(currentPage > numberOfPage || currentPage < 1 ) {
					currentPage = 1;	
				}
		
				String search="";
				if(request.getParameter("search") != null) {
					//ng dùng có nhập tìm kiếm
					search = request.getParameter("search");
				}
		Producer producerSearch = new Producer(search);
		List<Producer> listSearch = producerDAO.findAllByNameOderByNewName(producerSearch);
		List<Producer> producer = producerDAO.getProducerPagination(offset);
		
		if(!"".equals(search)) {
			producer = listSearch;
	}
		
		request.setAttribute("numberOfItem", numberOfItem);
		request.setAttribute("numberOfPage", numberOfPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("producer", producer);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/producer/index.jsp");
		rd.forward(request, response);


	}}
