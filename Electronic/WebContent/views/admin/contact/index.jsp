<%@page import="model.Contact"%>
<%@page import="constants.GlobalConstant"%>
<%@page import="model.Contact"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý liên hệ</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div class="row">
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="">
                                        <input type="submit"  class="btn btn-warning btn-sm"  style="float:right" />
                                        <input type="search" name="search" value="<% if(request.getParameter("search")!= null) out.print(request.getParameter("search")); %>" class="form-control input-sm" placeholder="Nhập tìm kiếm" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>
                            <%
                            	String msg="";
								if (request.getParameter("msg") != null) {
									 msg = request.getParameter("msg");
								}
							%>
							
								

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Num_Phone</th>
                                        <th>Address</th>
                                        <th>Message</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>    
                                <tbody>
                               <%
                               
      List<Contact> contacts = (List<Contact>)request.getAttribute("contact");
      for(Contact contact:contacts ){
    	  int id = contact.getId();
    	  String name = contact.getName();
    	  String email = contact.getEmail();
    	  String num_phone = contact.getNum_phone();
    	  String address = contact.getAddress();
    	  String message = contact.getMessage();
    	  String urlDel = request.getContextPath()+"/admin/contact/del?id="+id;
      
      %>
                                    <tr>
                                        <td><%=id %></td>
                                        <td class="center"><%=name %></td>
                                        <td class="center"><%=email %></td>
                                        <td class="center"><%=num_phone %></td>
                                        <td class="center"><%=address %></td>
                                        <td class="center"><%=message %></td>
                                        <td class="center">
                                     
                                            <a href="<%=request.getContextPath() %>/admin/contact/del?id=<%=id %>" title="" class="btn btn-danger" onclick="return confirm('bạn có chắc muốn xóa?')"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                                    <%} %>
                                </tbody>
                            </table>
                            <%
                          		int numberOfItem =(Integer) request.getAttribute("numberOfItem");
                            	int numberOfPage =(Integer) request.getAttribute("numberOfPage");
                           		int currentPage =(Integer) request.getAttribute("currentPage");
                           			 if(contacts != null && contacts.size() > 0){
                            %>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị <%=currentPage %> của <%=numberOfPage%> thông tin liên lạc</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/contact/index?page=<%if(currentPage==1) out.print(numberOfPage);else out.print(currentPage-1);%>">Trang trước</a></li>
                                            <% 
                                            	String active = "";
                                            for(int i=1; i<=numberOfPage; i++){
                                            	if(currentPage == i){
                                            		active = "active";
                                            	}else{
                                            		active = "";
                                            	}
                                            	%>
                                     <li class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/contact/index?page=<%=i%>"><%=i %></a></li>
                                               <%}%>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/contact/index?page=<%if(currentPage==numberOfPage) out.print(1);else out.print(currentPage+1);%>">Trang tiếp</a></li>
                                         
                                        </ul>
                                    </div>
                                </div>
                            </div>
                           <%}%>
                        </div>
   
                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("contact").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>