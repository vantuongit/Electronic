 <%@page import="model.Item"%>
<%@page import="model.Product"%>
<%@page import="daos.ProductDAO"%>
<%@page import="model.Producer"%>
<%@page import="java.util.List"%>
<%@page import="daos.ProducerDAO"%>
<div class="right_content">
       <div class="shopping_cart">
     
        <div class="cart_title">Shopping cart</div>
        
        <div class="cart_details" ><span id="num_card">0</span> items <br />
        
          <span class="border_cart"></span> Total: <span class="result"  id="total_price">0</span>$</div>
        <div class="cart_icon"><a href="<%=request.getContextPath() %>/my-cart" title="header=[Checkout] body=[&nbsp;] fade=[on]"><img src="<%=request.getContextPath() %>/resources/public/images/shoppingcart.png" alt="" width="48" height="48" border="0" /></a></div>
      </div>
      
      <div class="title_box">What's new</div>
     <%
      	ProductDAO productDAO = new ProductDAO();
      	List<Product> product = productDAO.getProductNew();
      	for(Product item: product){
     
      %>
      <div class="border_box">
        <div class="product_title"><a href="details.html"><%=item.getName() %></a></div>
        <div class="product_img"><a href="details.html"><img width="100px" height="100px" src="<%=request.getContextPath() %>/uploads/<%=item.getPicture() %>" alt="" border="0" /></a></div>
        <div class="prod_price"><span class="reduce"><%=item.getOld_price() %>$</span> <span class="price"><%=item.getPrice() %>$</span></div>
      </div>
      <%} %>
      <div class="title_box">Manufacturers</div>
      <ul class="left_menu">
      	  <%
      ProducerDAO producerDAO = new ProducerDAO();
		List<Producer> producers = producerDAO.getProducer();
		if(producers.size()>0){
		for(Producer producer:producers){
      	
      %>
        <li class="odd"><a href="<%=request.getContextPath()%>/producer?id=<%=producer.getId()%>"><%=producer.getName() %></a></li>
     <%}} %>
      </ul>
      <div class="banner_adds"> <a href="#"><img src="<%=request.getContextPath() %>/resources/public/images/bann1.jpg" alt="" border="0" /></a> </div>
    </div>