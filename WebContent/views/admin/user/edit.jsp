<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<%@page import="model.Users"%>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>sửa người dùng</h2>
            </div>
        </div>
        <!-- /. ROW  -->
         <%
                            if(request.getAttribute("user") != null){
                            	Users user = (Users) request.getAttribute("user");
                            	int id = user.getId();
                            	String username = user.getUsername();
                            	String password = user.getPassword();
                            	String fullname = user.getFullname();
                            	String number_phone = user.getNum_phone();
                            	String address = user.getAddress();
                            %>
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form role="form" action="<%=request.getContextPath()%>/admin/user/edit?id=<%=id%>" method="post"  id="form">
                                    <div class="form-group">
                                        <label for="username">Tên đăng nhập</label>
                                        <input type="text" pattern="[a-zA-Z0-9]{1,15}" id="username" value="<% if(username != null) out.print(username); %>" name="username" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="password">Mật khẩu:</label>
                                        <input type="password" id="password" value="<% if(password != null) out.print(password); %>" name="password" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="fullname">Họ tên: </label>
                                        <input type="text" id=""fullname"" value="<% if(fullname != null) out.print(fullname); %>" name="fullname" class="form-control" />
                                    </div>
                                     <div class="form-group">
                                        <label for="fullname">Số điện thoại: </label>
                                        <input type="text" id="number_phone" value="<% if(number_phone != null) out.print(number_phone); %>" name="number_phone" class="form-control" />
                                    </div>
                                     <div class="form-group">
                                        <label for="fullname">Địa chỉ:  </label>
                                        <input type="text" id="address" value="<% if(address != null) out.print(address); %>" name="address" class="form-control" />
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
                                </form>
                                <%} %>
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
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>