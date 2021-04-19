﻿<%@page import="constants.GlobalConstant"%>
<%@page import="model.Category" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý danh mục</h2>
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
                                <div class="col-sm-6">
                                    <a href="<%=request.getContextPath()%>/admin/cat/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="get" action="<%=request.getContextPath()%>/admin/cat/index">
                                        <input type="submit" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" name="search" class="form-control input-sm" value="<% if(request.getParameter("search")!= null) out.print(request.getParameter("search")); %>" placeholder="Nhập tên danh mục" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>
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

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên danh mục</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                @SuppressWarnings("unchecked")
                                	List<Category> categories = (List<Category>)request.getAttribute("categories");
                                	
                                if(categories != null && categories.size()>0 ){
                                	for(Category category :categories){
                                		int id = category.getId();
                                		String name = category.getName();
                                		String urlEdit = request.getContextPath()+"/admin/cat/edit?id="+id;
                                		String urlDel = request.getContextPath()+"/admin/cat/del?id="+id;
                                %>
                                    <tr>
                                        <td><%=id %></td>
                                        <td class="center"><%=name %></td>
                                        <td class="center">
                                            <a href="<%=urlEdit %>" title="Sửa" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=urlDel %>" title="Xóa"  onclick="return confirm('bạn có chắc muốn xóa?')" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                                   <%
                                   		 }
                            		   }
                              	  %>
                                </tbody>
                            </table>
                              <%
                            int numberOfItem =(Integer) request.getAttribute("numberOfItem");
                        	int numberOfPage =(Integer) request.getAttribute("numberOfPage");
                       		int currentPage =(Integer) request.getAttribute("currentPage");
                       		if(categories != null && categories.size()>0){
                            %>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ trang 1 đến trang <%=numberOfPage %> của <%=numberOfItem %> danh mục</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous">
                                            <a href="<%=request.getContextPath()%>/admin/cat/index?page=<%if(currentPage == 1){out.print(1);}else{out.print(currentPage -1);}%>">Trang trước</a></li>
                                              <%
                                        	String active = "";
                                            for(int i=1; i<=numberOfPage; i++){
                                            	if(currentPage == i){
                                            		active = "active";
                                            	}else{
                                            		active = "";
                                            	}
                                            %>
                                            <li class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/cat/index?page=<%=i%>"><%=i %></a></li>
                                            <%} %>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next">
                                            <a href="<%=request.getContextPath()%>/admin/cat/index?page=<%if(currentPage == numberOfPage){out.print(numberOfPage);}else{out.print(currentPage +1);}%>">Trang tiếp</a></li>
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
    document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>