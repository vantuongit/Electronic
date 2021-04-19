<%@page import="model.Item"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>check out</title>
</head>
<body>
<div class="row">
  <div class="col-75">
    <div class="container">
      <form  action="<%=request.getContextPath()%>/login-checkout" method="post" >
        <div class="row">
          <div class="col-50">
            <h3>Chưa đăng nhập, bấm đăng nhập hoặc điền thông tin vào form để mua hàng</h3>
            <a href="<%=request.getContextPath()%>/member/signin" style="text-decoration:none">ĐĂNG NHẬP</a><br/><br/>
            <label for="fname"><i class="fa fa-user"></i> Full Name</label>
            <input type="text" id="name" name="name"  placeholder="Nhập tên">
            <label for="adr"><i class="fa fa-address-card-o"></i> Number Phone</label>
            <input type="text" id="phone"  name="phone" placeholder="Nhập số dt">
            <label for="adr"><i class="fa fa-address-card-o"></i> Address</label>
            <input type="text" id="address" name="address" placeholder="Nhập địa chỉ">
          </div>
        </div>
        <label>
          <input type="checkbox" checked="checked" name="sameadr"> Shipping address same as billing
        </label>
        <input type="submit" value="Continue to checkout" class="btn">
      </form>
    </div>
  </div>

  <div class="col-25">
    <div class="container">
    <%
					if (session.getAttribute("listItem") != null) {
						List<Item> listItem = (List<Item>) session.getAttribute("listItem");
						float total = 0;
				%>
      <h4>Cart
        <span class="price" style="color:black">
          <i class="fa fa-shopping-cart"></i>
          <b><%=listItem.size() %></b>
        </span>
      </h4>
      <%
      
      	for(Item item :listItem){
      		total += item.getPrice()*item.getQty();
      %>
      <p><span href="#"><%=item.getProduct().getName() %></span> <span class="price"><%=item.getPrice()* item.getQty() %>$</span></p>
      <%} %>
      <hr>
      <p>Total <span class="price" style="color:black"><b><%=total %>$</b></span></p>
    </div>
    <%} %>
  </div>
</div>
</body>
<script type="text/javascript"  >
	$(document).ready(function () {
		$('#form_checkout').validate({
			rules: {
				"name": {
					required: true,
					minlength : 8,
					maxlength : 32,
				},
				"phone": {
					required: true,
				},
				"address": {
					required: true,
				},
			},
			messages: {
			  "name": {
				required : "Vui lòng nhập Tên",
				minlength : "Nhập tối thiểu 8 kí tự",
				maxlength : "nhập tối đa 32 kí tự",
			  },
			  "phone": {
				required: "Vui lòng nhập số điện thoại",
			  },
			  "address": {
					required: "Vui lòng nhập địa chỉ",
					minlength : "Nhập tối thiểu 25 kí tự",
					maxlength : "nhập tối đa 60 kí tự",
				  },
			},
		});
	});	
</script>
<style type="text/css">
.row {
  display: -ms-flexbox; /* IE10 */
  display: flex;
  -ms-flex-wrap: wrap; /* IE10 */
  flex-wrap: wrap;
  margin: 0 -16px;
}

.col-25 {
  -ms-flex: 25%; /* IE10 */
  flex: 25%;
}

.col-50 {
  -ms-flex: 50%; /* IE10 */
  flex: 50%;
}

.col-75 {
  -ms-flex: 75%; /* IE10 */
  flex: 75%;
}

.col-25,
.col-50,
.col-75 {
  padding: 0 16px;
}

.container {
  background-color: #f2f2f2;
  padding: 5px 20px 15px 20px;
  border: 1px solid lightgrey;
  border-radius: 3px;
}

input[type=text] {
  width: 100%;
  margin-bottom: 20px;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

label {
  margin-bottom: 10px;
  display: block;
}

.icon-container {
  margin-bottom: 20px;
  padding: 7px 0;
  font-size: 24px;
}

.btn {
  background-color: #4CAF50;
  color: white;
  padding: 12px;
  margin: 10px 0;
  border: none;
  width: 100%;
  border-radius: 3px;
  cursor: pointer;
  font-size: 17px;
}

.btn:hover {
  background-color: #45a049;
}

span.price {
  float: right;
  color: grey;
}

/* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other (and change the direction - make the "cart" column go on top) */
@media (max-width: 800px) {
  .row {
    flex-direction: column-reverse;
  }
  .col-25 {
    margin-bottom: 20px;
  }
}
</style>
</html>