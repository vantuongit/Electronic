package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ContactDAO;
import model.Contact;
import utils.AuthUtil;
import utils.DefineUtil;

public class AdminIndexContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexContactController() {
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
		
		ContactDAO contactDAO= new ContactDAO() ;
		int numberOfItem = contactDAO.numberOfItem(); //get number item cat
		int numberOfPage = (int)Math.ceil((float)numberOfItem/DefineUtil.NUMBER_PER_PAGE); // get số trang
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
		}
		int offset = (currentPage - 1)*DefineUtil.NUMBER_PER_PAGE; 		//nếu page ng dùng truyền vào lớn hơn hoặc nhỏ hơn numberOfPage
				if(currentPage > numberOfPage || currentPage < 1 ) {
					currentPage = 1;	
				}
				String search="";
				if(request.getParameter("search") != null) {
					//ng dùng có nhập tìm kiếm
					search = request.getParameter("search");
				}
				Contact contactSearch = new Contact(search);
				List<Contact> listSearch = contactDAO.findAllByNameOderByNewName(contactSearch);
		List<Contact> contact = contactDAO.getAllPagination(offset);
		if(!"".equals(search)) {
			contact = listSearch;
		}
		
		request.setAttribute("contact", contact);
		request.setAttribute("numberOfItem", numberOfItem);
		request.setAttribute("numberOfPage", numberOfPage);
		request.setAttribute("currentPage", currentPage);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/contact/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
