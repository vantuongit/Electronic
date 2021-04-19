<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<nav class="navbar-default navbar-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav" id="main-menu">
            <li class="text-center">
                <img src="<%=request.getContextPath() %>/resources/admin/assets/img/find_user.png" class="user-image img-responsive" />
            </li>
            <li>
                <a id="index" href="<%=request.getContextPath() %>/admin"><i class="fa fa-dashboard fa-3x"></i> Trang chủ</a>
            </li>
            <li>
                <a id="category" href="<%=request.getContextPath() %>/admin/cat/index"><i class="fa fa-list fa-3x"></i> Quản lý danh mục</a>
            </li>
            <li>
                <a id="producer" href="<%=request.getContextPath() %>/admin/producer/index"><i class="fa fa-list fa-3x"></i> Nhà sản xuất</a>
            </li>
            <li>
                <a id="product" href="<%=request.getContextPath() %>/admin/product/index"><i class="fa fa-laptop fa-3x"></i> Quản lý sản phẩm</a>
            </li>
            <li>
                <a id="user" href="<%=request.getContextPath() %>/admin/user/index"><i class="fa fa-user fa-3x"></i> Quản lý người dùng</a>
            </li>
            <li>
                <a id="order" href="<%=request.getContextPath() %>/admin/order"><i class="fa fa-user fa-3x"></i> Quản lý đơn hàng</a>
            </li>
            <li>
                <a id="contact" href="<%=request.getContextPath() %>/admin/contact/index"><i class="fa fa-envelope fa-3x"></i> Quản lý liên hệ</a>
            </li>
        </ul>
    </div>
</nav>
<!-- /. NAV SIDE  -->