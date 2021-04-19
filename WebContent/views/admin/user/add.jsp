<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Thêm người dùng</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
        String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        String num_phone = request.getParameter("num_phone");
        String address = request.getParameter("address");
        %>
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form role="form" action="" method="post"  id="form">
                                    <div class="form-group">
                                        <label for="username">Tên đăng nhập</label>
                                        <input type="text" id="username" pattern="[a-zA-Z0-9]{1,15}" value="<% if(username != null) out.print(username); %>" name="username" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="password">Mật khẩu:</label>
                                        <input type="password" id="password" value="" name="password" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="fullname">Họ tên: </label>
                                        <input type="text" id=""fullname"" value="<% if(fullname != null) out.print(fullname); %>" name="fullname" class="form-control" />
                                    </div>
                                     <div class="form-group">
                                        <label for="fullname">Số điện thoại: </label>
                                        <input type="text" id="num_phone" value="<% if(num_phone != null) out.print(num_phone); %>" name="num_phone" class="form-control" />
                                    </div>
                                     <div class="form-group">
                                        <label for="fullname">Địa chỉ: </label>
                                        <input type="text" id="address" value="<% if(address != null) out.print(address); %>" name="address" class="form-control" />
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Thêm</button>
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
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>