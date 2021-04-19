<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<%@page import="java.util.List" %>
<%@page import="model.Users" %>
<%@page import="constants.GlobalConstant"%>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý người dùng</h2>
            </div>
            </div>
            <%
            	String err = request.getParameter("err");
            	String msgs = request.getParameter("msgs");
            	if("1".equals(err)){ %>
            		<div class="alert alert-warning" role="alert">
            		  Không có quyền thêm!
            		</div>
            	<%}
            	if("2".equals(err)){
            		%>
            		<div class="alert alert-warning" role="alert">
            		  Không có quyền sửa!
            		</div>
            	<%
            	}
            	if("3".equals(err)){
            		%>
            		<div class="alert alert-warning" role="alert">
            		  Không tồn tại!
            		</div>
            	<%
            	}
            	if("4".equals(err)){
            		%>
            		<div class="alert alert-warning" role="alert">
            		  Không có quyền xóa!
            		</div>
            	<%
            	}if("5".equals(err)){
            %>
            <div class="alert alert-warning" role="alert">
            		  Trùng tên!!
            		</div>
            		<%} %>
        
        <!-- /. ROW  -->
         <%
                            	if(request.getParameter("msg") != null){
                            		String msg = request.getParameter("msg");
                            		if(GlobalConstant.SUCCESS.equals(msg)){
                            %>
                            	<h4 class= "SUCCESS"> XỬ LÝ THÀNH CÔNG!</h4>
                            <%
                            		}else{
                            %>
                            <h4> Xử lý thất bại !</h4>
                            <%		
                            		}
                            	}
                            %>
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div class="row">
                                <div class="col-sm-6">
                                    <a href="<%=request.getContextPath()%>/admin/user/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="get" action="<%=request.getContextPath()%>/admin/user/index">
                                        <input type="submit"  class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" name="search" value="<% if(request.getParameter("search")!= null) out.print(request.getParameter("search")); %>" class="form-control input-sm" placeholder="Nhập tên người dùng" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                          
                                
                                    <tr>
                                        <th>ID</th>
                                        <th>username</th>
                                        <th>fullname</th>
                                        <th>number_phone</th>
                                        <th>address</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                            List<Users> users = (List<Users>)request.getAttribute("users");
                            
                            if(users != null && users.size()>0 ){
                            	for(Users user :users){
                            		int id = user.getId();
                            		String username = user.getUsername();
                            		String fullname = user.getFullname();
                            		String number_phone = user.getNum_phone();
                            		String address = user.getAddress();
                            		String urlDel = request.getContextPath()+"/admin/user/del?id="+id;
                            		String urlEdit = request.getContextPath()+"/admin/user/edit?id="+id;
                            	
                            %>
                            
                                    <tr>
                                        <td><%=id %></td>
                                        <td class="center"><%=username %></td>
                                        <td class="center"><%=fullname %></td>
                                        <td class="center"><%=number_phone %></td>
                                        <td class="center"><%=address %></td>
                                        <%
                                         Users userInfo = (Users) session.getAttribute("userInfo");
                                        	if("admin".equals(userInfo.getUsername())){
                                        %> 
                                        <td class="center">
                                            <a href="<%=urlEdit %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=urlDel %>" title="" onclick="return confirm('bạn có chắc muốn xóa?')" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                     	</td>
                                     <%}else{ 
                                    	   if(userInfo.getId() == user.getId()){
                                    	   %>
                                    	   <td class="center">
                                       		 <a href="<%=urlEdit %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
											</td>                                      
                                       <%}} %> 
                                       
                                    </tr>
                                    <%}} %>
                                </tbody>
                            </table>
                            
                            <%
                          		int numberOfItems =(Integer) request.getAttribute("numberOfItems");
                            	int numberOfPage =(Integer) request.getAttribute("numberOfPage");
                           		int currentPage =(Integer) request.getAttribute("currentPage");
                           			 if(users != null && users.size() > 0){
                            %>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ trang 1 đến trang <%=numberOfPage %> của <%=numberOfItems %> người dùng</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/user/index?page=<%if(currentPage == 1){out.print(1);}else{out.print(currentPage -1);}%>">Trang trước</a></li>
                                            <% 
                                            	String active = "";
                                            for(int i=1; i<=numberOfPage; i++){
                                            	if(currentPage == i){
                                            		active = "active";
                                            	}else{
                                            		active = "";
                                            	}
                                            	%>
                                            <li class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/user/index?page=<%=i%>"><%=i %></a></li>
                                          		<%} %>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/user/index?page=<%if(currentPage == numberOfPage){out.print(numberOfPage);}else{out.print(currentPage +1);}%>">Trang tiếp</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <%} %>
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>