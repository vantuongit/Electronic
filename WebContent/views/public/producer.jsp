<%@page import="utils.StringUtil"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.List"%>
<%@page import="model.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/templates/public/inc/header.jsp" %>
    
 
 
    <div class="crumb_navigation"> Navigation: <span class="current">Home</span> </div>
<%@ include file="/templates/public/inc/leftbar.jsp" %>
    <!-- end of left content -->
    <div class="center_content">
			<%
			Producer producerss = (Producer) request.getAttribute("producer");
	  		  if(producerss != null){
			%>
      <div class="center_title_bar"><%=producerss.getName() %></div>
      		<%} %>
              <%
					List<Product> products =(List<Product>)request.getAttribute("products");
   					if(products.size()>0 && products != null){
   						int i=0;
						for(Product product: products){
							i++;
							
							int id = product.getId();
							String productName = product.getName();
							/* productName = productName.replaceAll(searchs, text); */
						
							int counter = product.getCounter();
							String picture = product.getPicture();
							String catName = product.getCategory().getName();
							String producerName = product.getProducer().getName();
							Float price = product.getPrice();
							int old_price = product.getOld_price();
							int count = product.getCounter();
							Timestamp date_create = product.getDate_create();
           
 %>
      <div class="prod_box"> 
        <div class="top_prod_box"></div>
        <div class="center_prod_box">
          <div class="product_title"><a href="<%=request.getContextPath()%>/detail?id=<%=id %>"><%=producerName  %></a></div>
          <div class="product_img"><a href="<%=request.getContextPath()%>/detail?id=<%=id %>"><img width ="75px" height="75px" src="<%=request.getContextPath() %>/uploads/<%=picture%>?id=<%=id %>" alt="" border="0" /></a></div>
          <div class="prod_price"><span class="reduce"><%=old_price %>$</span> <span class="price"><%=price %>$</span></div>
        </div>
        <div class="bottom_prod_box"></div>
        <div class="prod_details_tab"> <a href="#" onclick="return buy_product(<%=id %>)" title="header=[Add to cart] body=[&nbsp;] fade=[on]"><img src="<%=request.getContextPath() %>/resources/public/images/cart.gif" alt="" border="0" class="left_bt" /></a> <a href="#" title="header=[Specials] body=[&nbsp;] fade=[on]"><img src="<%=request.getContextPath() %>/resources/public/images/favs.gif" alt="" border="0" class="left_bt" /></a> <a href="#" title="header=[Gifts] body=[&nbsp;] fade=[on]"><img src="<%=request.getContextPath() %>/resources/public/images/favorites.gif" alt="" border="0" class="left_bt" /></a> <a href="details.html" class="prod_details">details</a> </div>
      </div>
     <%} %>
    </div>
    <%} %>
    <!-- end of center content -->
     <!-- AJAX CART -->
     <script type="text/javascript">
 	function buy_product(id){
		//Handing Ajax
		$.ajax({
		url: '<%=request.getContextPath()%>/buy_product',
		type : 'POST',
		cache : false,
		data : {
			"AidProduct" : id
		},
		success : function(data) {
			$("#num_card").html(data);
			alert("đã thêm vào giỏ hàng");
		},
		error : function() {
			alert('Có lỗi xảy ra');
		}
	});
	return false;
}
	
</script>
   <%@ include file="/templates/public/inc/rightbar.jsp" %>
    <!-- end of right content -->
  </div>
  <!-- end of main content -->
  <%@ include file="/templates/public/inc/footer.jsp" %>
  