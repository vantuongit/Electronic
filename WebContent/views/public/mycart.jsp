
<%@page import="java.util.List"%>
<%@page import="model.Item"%>
<%@page import="model.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Cart</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/public/js/jquery-3.5.1.min.js"></script>

</head>
<body>
	<div class="container">
		<div class="heading">
		<%
      	String msg = request.getParameter("msg");
      if("1".equals(msg)){
    	  out.print("<div style=\"background: yellow; color: red; font-weight: bold; padding: 5px; text-align: center; margin-left: 10px; \">mua hàng thành công</div>");
      }
      if("error".equals(msg)){
    	  out.print("<div style=\"background: yellow; color: red; font-weight: bold; padding: 5px; text-align: center; margin-left: 10px; \">mua hàng thất bại</div>");
      }
      %>
			<h1>
				<span class="shopper">s</span> Shopping Cart
			</h1>

			<a href="<%=request.getContextPath()%>/home"
				class="visibility-cart transition is-open">X</a>
		</div>

		<div class="cart transition is-open">

			<a href="<%=request.getContextPath()%>/home" class="btn btn-update">HOME</a>


			<div class="table">
			

				<div class="layout-inline row th">
					<div class="col col-pro">Product</div>
					<div class="col col-price align-center ">Price</div>
					<div class="col col-qty align-center">QTY</div>
					<div class="col">Total</div>
				</div>
				<%
				
				if (session.getAttribute("listItem") != null) {
					List<Item> listItem = (List<Item>) session.getAttribute("listItem");
					/* float totals =(float) session.getAttribute("tot"); */
			
				float tongtien=0;
					for (Item item : listItem) {
							tongtien +=item.getQty() * item.getPrice();
				%>

				<div class="layout-inline row">

					<div class="col col-pro layout-inline">
						<img
							src="<%=request.getContextPath()%>/uploads/<%=item.getProduct().getPicture()%>"
							alt="" />
						<p><%=item.getProduct().getName()%></p>
					</div>
					<div class="col col-price col-numeric align-center ">
						<p id = "giaban"><%=item.getPrice()%>$</p>
					</div>

					<div class="col col-qty layout-inline">
						<a href="javascript:void(0)" class="qty qty-minus" id="subItem_<%=item.getId() %>" onclick="return myFunction(this,<%=item.getId()%>,'reduc')">-</a> 
						<input type="numeric" id="qty<%=item.getId() %>" value="<%=item.getQty()%>" /> 
						<a href="javascript:void(0)" class="qty qty-plus" onclick="return myFunction(this,<%=item.getId()%>,'incre')">+</a>
					</div>

					<div class="col col-total col-numeric">
						 <p id="price<%=item.getId()%>"><%=item.getPrice()%></p>
					</div>
				</div>
				<script>
					function myFunction(obj,idItem,chk) {
						//console.log(obj);
						$.ajax({
							url: '<%=request.getContextPath()%>/my-cart',
							type : 'POST',
							cache : false,
							data : {
								"idProduct" : idItem,
								"achk" : chk,
							},
							success : function(data) {
								console.log(data);
								if(data !=""){
								$("#qty"+idItem).val(data);
								$("#price"+idItem).html(data*<%=item.getPrice()%>);
								}else{
									alert("k có sản phẩm");
								}
							},
							error : function() {
								alert('Có lỗi xảy ra');
							}
						});
						return false;
					}
				</script> 
					<%
					}
					%>
				
				<div class="tf">
					<div class="row layout-inline">
						<div class="col">
							<p>VAT</p>
						</div>
						<div class="col"></div>
					</div>
					<div class="row layout-inline">
						<div class="col">
							<p>Shipping</p>
						</div>
						<div class="col"></div>
					</div>
					<div class="row layout-inline">
						<div class="col">
							<p>
								Total: <span><%=tongtien%></span>
							</p>
						</div>
					
						<div class="col"></div>
					</div>
				</div>
			</div>
			<%} %>

			<a href="<%=request.getContextPath() %>/check-out" class="btn btn-update">Check Out</a>

		</div>
</body>
<style type="text/css">
html {
	background: #bbc3c6;
	font: 62.5%/1.5em "Trebuchet Ms", helvetica;
}

* {
	box-sizing: border-box;
	-webkit-font-smoothing: antialiased;
	font-smoothing: antialiased;
}

@font-face {
	font-family: 'Shopper';
	src: url('http://www.shopperfont.com/font/Shopper-Std.ttf');
}

.shopper {
	text-transform: lowercase;
	font: 2em 'Shopper', sans-serif;
	line-height: 0.5em;
	display: inline-block;
}

h1 {
	text-transform: uppercase;
	font-weight: bold;
	font-size: 2.5em;
}

p {
	font-size: 1.5em;
	color: #8a8a8a;
}

input {
	border: 0.3em solid #bbc3c6;
	padding: 0.5em 0.3em;
	font-size: 1.4em;
	color: #8a8a8a;
	text-align: center;
}

img {
	max-width: 9em;
	width: 100%;
	overflow: hidden;
	margin-right: 1em;
}

a {
	text-decoration: none;
}

.container {
	max-width: 75em;
	width: 95%;
	margin: 40px auto;
	overflow: hidden;
	position: relative;
	border-radius: 0.6em;
	background: #ecf0f1;
	box-shadow: 0 0.5em 0 rgba(138, 148, 152, 0.2);
}

.heading {
	padding: 1em;
	position: relative;
	z-index: 1;
	color: #f7f7f7;
	background: #f34d35;
}

.cart {
	margin: 2.5em;
	overflow: hidden;
}

.cart.is-closed {
	height: 0;
	margin-top: -2.5em;
}

.table {
	background: #ffffff;
	border-radius: 0.6em;
	overflow: hidden;
	clear: both;
	margin-bottom: 1.8em;
}

.layout-inline>* {
	display: inline-block;
}

.align-center {
	text-align: center;
}

.th {
	background: #f34d35;
	color: #fff;
	text-transform: uppercase;
	font-weight: bold;
	font-size: 1.5em;
}

.tf {
	background: #f34d35;
	text-transform: uppercase;
	text-align: right;
	font-size: 1.2em;
}

.tf p {
	color: #fff;
	font-weight: bold;
}

.col {
	padding: 1em;
	width: 12%;
}

.col-pro {
	width: 44%;
}

.col-pro>* {
	vertical-align: middle;
}

.col-qty {
	text-align: center;
	width: 17%;
}

.col-numeric p {
	font: bold 1.8em helvetica;
}

.col-total p {
	color: #12c8b1;
}

.tf .col {
	width: 20%;
}

.row>div {
	vertical-align: middle;
}

.row-bg2 {
	background: #f7f7f7;
}

.visibility-cart {
	position: absolute;
	color: #fff;
	top: 0.5em;
	right: 0.5em;
	font: bold 2em arial;
	border: 0.16em solid #fff;
	border-radius: 2.5em;
	padding: 0 0.22em 0 0.25em;
}

.col-qty>* {
	vertical-align: middle;
}

.col-qty>input {
	max-width: 2.6em;
}

a.qty {
	width: 1em;
	line-height: 1em;
	border-radius: 2em;
	font-size: 2.5em;
	font-weight: bold;
	text-align: center;
	background: #43ace3;
	color: #fff;
}

a.qty:hover {
	background: #3b9ac6;
}

.btn {
	padding: 10px 30px;
	border-radius: 0.3em;
	font-size: 1.4em;
	font-weight: bold;
	background: #43ace3;
	color: #fff;
	box-shadow: 0 3px 0 rgba(59, 154, 198, 1)
}

.btn:hover {
	box-shadow: 0 3px 0 rgba(59, 154, 198, 0)
}

.btn-update {
	float: right;
	margin: 0 0 1.5em 0;
}

.transition {
	transition: all 0.3s ease-in-out;
}

@media screen and ( max-width: 755px) {
	.container {
		width: 98%;
	}
	.col-pro {
		width: 35%;
	}
	.col-qty {
		width: 22%;
	}
	img {
		max-width: 100%;
		margin-bottom: 1em;
	}
}

@media screen and ( max-width: 591px) {
}
</style>

<script type="text/javascript">
	$('.visibility-cart').on('click', function() {

		var $btn = $(this);
		var $cart = $('.cart');
		console.log($btn);

		if ($btn.hasClass('is-open')) {
			$btn.removeClass('is-open');
			$btn.text('O')
			$cart.removeClass('is-open');
			$cart.addClass('is-closed');
			$btn.addClass('is-closed');
		} else {
			$btn.addClass('is-open');
			$btn.text('X')
			$cart.addClass('is-open');
			$cart.removeClass('is-closed');
			$btn.removeClass('is-closed');
		}

	});

	// SHOPPING CART PLUS OR MINUS
	$('a.qty-minus').on('click', function(e) {
		e.preventDefault();
		var $this = $(this);
		var $input = $this.closest('div').find('input');
		var value = parseInt($input.val());

		if (value > 1) {
			value = value - 1;
		} else {
			value = 0;
		}

		$input.val(value);

	});

	$('a.qty-plus').on('click', function(e) {
		e.preventDefault();
		var $this = $(this);
		var $input = $this.closest('div').find('input');
		var value = parseInt($input.val());

		if (value < 100) {
			value = value + 1;
		} else {
			value = 100;
		}

		$input.val(value);
	});

	// RESTRICT INPUTS TO NUMBERS ONLY WITH A MIN OF 0 AND A MAX 100
	$('input').on('blur', function() {

		var input = $(this);
		var value = parseInt($(this).val());

		if (value < 0 || isNaN(value)) {
			input.val(0);
		} else if (value > 100) {
			input.val(100);
		}
	});
</script>
</html>