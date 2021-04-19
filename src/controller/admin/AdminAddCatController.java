package controller.admin;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.fabric.Response;

import constants.GlobalConstant;
import daos.CategoryDAO;
import model.Category;
import utils.AuthUtil;

public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminAddCatController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CHECK LOGIN
		if(!AuthUtil.checkLogin(request,response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
				
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CHECK LOGIN
		if(!AuthUtil.checkLogin(request,response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		//phân quyền
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfo") == null) {
			response.sendRedirect(request.getContextPath()+ "/auth/login");
			return;
		}
		CategoryDAO categoryDAO = new CategoryDAO();
		String name ="";
		if(!"".equals(request.getParameter("name"))) {
		 name = request.getParameter("name");
		}
		
//		Pattern p = Pattern.compile("[A-Za-z0-9]");
//	    Matcher m = p.matcher(name);
//	    boolean b = m.find();
//		if(b == true) {  
		
		Category category = new Category(name);
		//check trùng tên
		if(categoryDAO.hasCategories(name)) { // khi trùng tên thì gửi về lại cat inndex
			response.sendRedirect(request.getContextPath()+"/admin/cat/index?err=1");
			return;
		}
		int countRecordInserted = categoryDAO.add(category);
		StringBuilder sbd = new StringBuilder();
		String url = "";
		if(countRecordInserted > 0) {
			url = sbd.append(request.getContextPath())
					.append("/admin/cat/index?msg=")
					.append(GlobalConstant.SUCCESS).toString();
			response.sendRedirect(url);
			return;
		}
		//fail
		response.sendRedirect(request.getContextPath()+"/admin/cat/index?msg="+GlobalConstant.ERROR);
		
	}

}
