<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="daos.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@page import="model.Member"%>
<html xmlns="http://www.w3.org/1999/xhtml" />
<head>
<title>Electronix Store</title>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/public/css/style.css" />
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="resources/public/css/iecss.css" />
<![endif]-->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/public/js/boxOver.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/public/js/jquery-3.5.1.min.js"></script>
</head>
<body>
<div id="main_container">
  <div class="top_bar">
  <form  method="get" action="<%=request.getContextPath()%>/home" >
    <div class="top_search">
      <div class="search_text"><a href="#">Advanced Search</a></div>
      <input type="text"  name="search" class="search_input"placeholder="Nhập từ khóa tìm kiếm" value = "<%if(request.getParameter("search")!=null) out.print(request.getParameter("search")); %>" />
      <input type="image"  name="button_search"  src="<%=request.getContextPath() %>/resources/public/images/search.gif" class="search_bt"/>
    </div>
    </form>
    <div class="languages">
      <div class="lang_text">Languages:</div>
      <a href="#" class="lang"><img src="<%=request.getContextPath() %>/resources/public/images/en.gif" alt="" border="0" /></a> <a href="#" class="lang"><img src="<%=request.getContextPath() %>/resources/public/images/de.gif" alt="" border="0" /></a> </div>
  </div>
  <div id="header">
    <div id="logo"> <a href="#"><img src="<%=request.getContextPath() %>/resources/public/images/logo.png" alt="" border="0" width="237" height="140" /></a> </div>
    <div class="oferte_content">
      <div class="top_divider"><img src="<%=request.getContextPath() %>/resources/public/images/header_divider.png" alt="" width="1" height="164" /></div>
      <div class="oferta">
     
        <div class="oferta_content" >
        <%--  <img src="<%=request.getContextPath() %>/resources/public/images/laptop.png" width="94" height="92" alt="" border="0" class="oferta_img" />
          <div class="oferta_details">
            <div class="oferta_title">Samsung GX 2004 LM</div>
            <div class="oferta_text"> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco </div>
            <a href="details.html" class="details">details</a> </div> --%>
            
          <a href="http://phongvu.vn"><img  class="oferta_img" width="650px" height="150" src="https://lh3.googleusercontent.com/3rXxhlhXeosO-S5HzqLoyTMkjOfLoOduME-6IK8FW2gEjBcTwbOePNuPxYnsJGgNcRkuOMc5rQaO8aayJ-N_T2j6HhWOYLA2=w1920-rw" alt="" /></a> 
       <!-- <video controls="controls" 
			src="https://www.youtube.com/watch?v=SJC6kMv5Cq0" type="video/mp4">
		</video> -->
        </div>
       
        <!-- <div class="oferta_pagination"> <span class="current">1</span> <a href="#">2</a> <a href="#">3</a> <a href="#">4</a> <a href="#">5</a> </div> -->
      </div>
      <div class="top_divider"><img src="<%=request.getContextPath() %>/resources/public/images/header_divider.png" alt="" width="1" height="164" /></div>
    </div>
    <!-- end of oferte_content-->
  </div>
  <div id="main_content">
    <div id="menu_tab">
      <div class="left_menu_corner"></div>
      <ul class="menu">
        <li><a href="<%=request.getContextPath()%>/home" class="nav1"> Home</a></li>
        <li class="divider"></li>
        <li><a href="<%=request.getContextPath()%>/product" class="nav2">Products</a></li>
        <li class="divider"></li>
        <li><a href="#" class="nav3">Specials</a></li>
        <li class="divider"></li>
        <li><a href="#" class="nav4">My account</a></li>
        
        <li class="divider"></li>
         <%
                     Member userInfo = (Member) session.getAttribute("userInfo");
                     if(userInfo != null){
         %>
        <li><a href="<%=request.getContextPath() %>/member/logout" class="nav4"><%=userInfo.getFullname() %></a></li>
        <%}else{ %>
        <li><a href="<%=request.getContextPath() %>/member/signin" class="nav4">Sign in</a></li>
        <%} %>
        <li class="divider"></li>
        <li><a href="#" class="nav5">Shipping</a></li>
        <li class="divider"></li>
        <li><a href="<%=request.getContextPath() %>/contact" class="nav6">Contact Us</a></li>
        <li class="divider"></li>
        <li class="currencies">Currencies
          <select>
            <option>US Dollar</option>
            <option>Euro</option>
          </select>
        </li>
      </ul>
      <div class="right_menu_corner"></div>
    </div>
    <!-- end of menu tab -->