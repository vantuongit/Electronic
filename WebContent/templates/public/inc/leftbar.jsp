    <%@page import="daos.ProductDAO"%>
<%@page import="daos.ProducerDAO"%>
<%@page import="model.Product"%>
<%@page import="daos.CategoryDAO"%>
<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<div class="left_content">
      <div class="title_box">Categories</div>
      <ul class="left_menu">
      <%
      CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> categories = categoryDAO.getCategories();
		if(categories.size()>0){
		for(Category category:categories ){
      	
      %>
        <li class="odd"><a href="<%=request.getContextPath()%>/cat?id=<%=category.getId()%>"><%=category.getName() %></a></li>
        <%} %>
      </ul>
      <div class="title_box">Special Products</div>
      <%
      	ProductDAO productDAO = new ProductDAO();
      	List<Product> product = productDAO.getProductSpecial();
      	for(Product item: product){
     
      %>
      <div class="border_box">
        <div class="product_title"><a href="<%=request.getContextPath()%>/detail?id=<%=item.getId() %>"><%=item.getName() %></a></div>
        <div class="product_img"><a href="<%=request.getContextPath()%>/detail?id=<%=item.getId() %>"><img width="100px" height="100px" src="<%=request.getContextPath() %>/uploads/<%=item.getPicture() %>" alt="" border="0" /></a></div>
        <div class="prod_price"><span class="reduce"><%=item.getOld_price() %>$</span> <span class="price"><%=item.getPrice() %>$</span></div>
      </div>
      <%} %>
  <%--     <div class="title_box">Newsletter</div>
      <div class="border_box">
        <input type="text" name="newsletter" class="newsletter_input" value="your email"/>
        <a href="#" class="join">join</a> </div>--%>
      <div class="banner_adds"> <a href="#"><img src="<%=request.getContextPath() %>/resources/public/images/bann2.jpg" alt="" border="0" /></a> </div> 
      <%}%>
    </div>