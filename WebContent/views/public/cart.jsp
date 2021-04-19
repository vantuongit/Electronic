<%@page import="model.Item"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="shopping_cart">
     <%
			if (session.getAttribute("listItem") != null) {
				ArrayList<Item> listItem = (ArrayList<Item>) session.getAttribute("listItem");
		%>
        <div class="cart_title">Shopping cart</div>
        <%
				float total=0;
        		int quanty=0;
				for(Item objHoa: listItem){
					total +=objHoa.getQty() * objHoa.getPrice();
					quanty +=objHoa.getQty() + 1;
					
				}
			%>
        <div class="cart_details" ><span id="num_card"><%=quanty %></span> items <br />
        
          <span class="border_cart"></span> Total: <span class="result"  id="total_price"><%=total %></span>$</div>
          <%} %>
        <div class="cart_icon"><a href="<%=request.getContextPath() %>/my-cart" title="header=[Checkout] body=[&nbsp;] fade=[on]"><img src="<%=request.getContextPath() %>/resources/public/images/shoppingcart.png" alt="" width="48" height="48" border="0" /></a></div>
      </div>
</body>
</html>