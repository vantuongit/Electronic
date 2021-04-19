<%@page import="model.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/templates/public/inc/header.jsp" %>
    <div class="crumb_navigation"> Navigation: <a href="#">Home</a> &lt; <span class="current">Category name</span> </div>
<%@ include file="/templates/public/inc/leftbar.jsp" %>
   
    <!-- end of left content -->
    
     <%
    	Product productdetail = (Product) request.getAttribute("product");
   		if(productdetail != null){
   			
    %>
    <div class="center_content">
      <div class="center_title_bar"><%=productdetail.getCategory().getName() %></div>
      <div class="prod_box_big">
        <div class="top_prod_box_big"></div>
        <div class="center_prod_box_big">
          <div class="product_img_big"> <a href="javascript:popImage('images/big_pic.jpg','Some Title')" title="header=[Zoom] body=[&nbsp;] fade=[on]"><img width = "100px" height="100px" src="<%=request.getContextPath() %>/uploads/<%=productdetail.getPicture() %>" alt="" border="0" /></a>
            <div class="thumbs"> <a href="#" title="header=[Thumb1] body=[&nbsp;] fade=[on]"><img src="<%=request.getContextPath() %>/resources/public/images/thumb1.gif" alt="" border="0" /></a> <a href="#" title="header=[Thumb2] body=[&nbsp;] fade=[on]"><img src="<%=request.getContextPath() %>/resources/public/images/thumb1.gif" alt="" border="0" /></a> <a href="#" title="header=[Thumb3] body=[&nbsp;] fade=[on]"><img src="<%=request.getContextPath() %>/resources/public/images/thumb1.gif" alt="" border="0" /></a> </div>
          </div>
          <div class="details_big_box">
            <div class="product_title_big"><%=productdetail.getName() %></div>
            <div class="specifications"> Mô tả: <span class="blue"><%=productdetail.getDetail() %></span><br />
            </div>
            <div class="prod_price_big"><span class="reduce"><%=productdetail.getOld_price() %>$</span> <span class="price"><%=productdetail.getPrice() %>$</span></div>
            <a href="javascript:void(0)" onclick="return buy_product(<%=productdetail.getId() %>)" class="addtocart">add to cart</a> <a href="#" class="compare">compare</a> </div>
        </div>
        <div class="bottom_prod_box_big"></div>
      </div>
      <div class="center_title_bar">Similar products</div>
      <%
      	List<Product> relatedProduct = (List<Product>) request.getAttribute("relatedProduct");
      	if(relatedProduct != null && relatedProduct.size() > 0){
      		for(Product relatedProducts : relatedProduct){
      %>
      <div class="prod_box">
        <div class="top_prod_box"></div>
        <div class="center_prod_box">
          <div class="product_title"><a href="<%=request.getContextPath()%>/detail?id=<%=relatedProducts.getId() %>"><%if(relatedProducts.getName().length()>15) out.print(relatedProducts.getName().substring(0, 15)); else out.print(relatedProducts.getName()); %>...</a></div>
          <div class="product_img"><a href="<%=request.getContextPath()%>/detail?id=<%=relatedProducts.getId() %>"><img width ="100px" height="100px" src="<%=request.getContextPath() %>/uploads/<%=relatedProducts.getPicture() %>" alt="" border="0" /></a></div>
          <div class="prod_price"><span class="reduce"><%=relatedProducts.getOld_price() %>$</span> <span class="price"><%=relatedProducts.getPrice() %>$</span></div>
        </div>
        <div class="bottom_prod_box"></div>
        <div class="prod_details_tab"> <a href="javascript:void(0)" onclick="return buy_product(<%=relatedProducts.getId() %>)" title="header=[Add to cart] body=[&nbsp;] fade=[on]"><img src="<%=request.getContextPath() %>/resources/public/images/cart.gif" alt="" border="0" class="left_bt" /></a> <a href="#" title="header=[Specials] body=[&nbsp;] fade=[on]"><img src="<%=request.getContextPath() %>/resources/public/images/favs.gif" alt="" border="0" class="left_bt" /></a> <a href="#" title="header=[Gifts] body=[&nbsp;] fade=[on]"><img src="<%=request.getContextPath() %>/resources/public/images/favorites.gif" alt="" border="0" class="left_bt" /></a> <a href="details.html" class="prod_details">details</a> </div>
      </div>
    <%}}else{ %>
    	<div class="prod_box">Không có sản phẩm !!!</div>
    <%}} %>
    </div>
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
  
    <!-- end of center content -->
     <%@ include file="/templates/public/inc/rightbar.jsp" %>
    <!-- end of right content -->
  </div>
  <!-- end of main content -->
   <%@ include file="/templates/public/inc/footer.jsp" %>