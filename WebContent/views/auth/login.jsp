<%@page import="constants.GlobalConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Đăng Nhập Hệ Thống</h2>
               
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                               <%
                               	if(request.getParameter("msg") != null){
                               %>
                            <div class="alert alert-danger" role="alert">
 										 Sai tên đăng nhập hoặc mật khẩu, vui lòng thử lại!
							</div>	
                               <%} %>
                                <form role="form" method="post" id="form" action="<%=request.getContextPath()%>/auth/login">
                                    <div class="form-group">
                                        <label for="name">Đăng nhập</label>
                                        <input type="text" id="username" value="" name="username" class="form-control" />
                                    </div>
                                     <div class="form-group">
                                        <label for="name">Mật khẩu</label>
                                        <input type="password" id="password" value="" name="password" class="form-control" />
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Đăng nhập</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Form Elements -->
            </div>
        </div>
        <!-- /. ROW  -->
    </div>
    <!-- /. PAGE INNER  -->
</div>
<script>
    document.getElementById("login").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>