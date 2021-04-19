package controller.publics;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.GlobalConstant;
import constants.UrlConstants;
import daos.ContactDAO;
import model.Contact;

public class PublicContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicContactController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/contact.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String num_phone = request.getParameter("num_phone");
		String address = request.getParameter("address");
		String message = request.getParameter("message");
		
		ContactDAO contactDAO = new ContactDAO();
		Contact item = new Contact(0, name, email, num_phone, address, message);
		if(contactDAO.addItem(item) > 0) {
			response.sendRedirect(request.getContextPath() + "/contact?msg="+GlobalConstant.SUCCESS);
			return;
		}else {
				RequestDispatcher rd = request.getRequestDispatcher("/views/public/contact.jsp?msg="+GlobalConstant.ERROR);
				rd.forward(request, response);
		}
	}

}
