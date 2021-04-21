package controller.admin;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ProductDAO;
import model.Product;

public class AdminDelProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private static ProductDAO productDAO;
    public AdminDelProductController() {
        super();
        productDAO = new daos.ProductDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/product/index");
			return;
		}
		
		Product product = productDAO.getItem(id);
		if(product == null) {
			response.sendRedirect(request.getContextPath() + "/admin/product/index");
			return;
		}
		if(productDAO.delItem(id)>0) {
			//thành công
			
			//xóa ảnh
			final String dirPathName = request.getServletContext().getRealPath("/uploads");
			String picture = product.getPicture();
			if(!picture.isEmpty()) {
			String filePathName = dirPathName + File.separator + picture;
			File file = new File(filePathName);
			if(file.exists()) {
				file.delete();
			}
			}
			
			response.sendRedirect(request.getContextPath() + "/admin/product/index?msg=3");
			return;
		}else {
			//thất bại
			response.sendRedirect(request.getContextPath() + "/admin/product/index?err=3");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
	}

}
