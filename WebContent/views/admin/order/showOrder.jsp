<%@page import="model.Item"%>
<%@page import="model.Order"%>
<%@page import="constants.GlobalConstant"%>
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
                <h2>Quản lý đơn hàng</h2>
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
                                        <input type="submit"  class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" name="search" value="<% if(request.getParameter("search")!= null) out.print(request.getParameter("search")); %>" class="form-control input-sm" placeholder="Nhập tên danh mục" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID Hóa Đơn</th>
                                        <th>Tên SP</th>
                                        <th>Số lượng</th>
                                        <th>Giá tiền</th>
                                        <th>Thành tiền</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                @SuppressWarnings("unchecked")
                                List<Item> listItem = (List<Item>)request.getAttribute("listShowOrder");
                              
                                if(listItem != null ){
									for(Item items: listItem) {
										int id = items.getId();
										String name = items.getProduct().getName();
										int qty = items.getQty();
										float price = items.getPrice();
									
                                	
                                			
                                %>
                                    <tr>
                                        <td><%=id %></td>
                                        <td class="center"><%=name %></td>
                                        <td class="center"><%=qty %></td>
                                        <td class="center"><%=price %></td>
                                        <td class="center"> <%=price * qty %></td>
                                    </tr>
                                   <%
									} 
									
                                }
                              	  %>
                                </tbody>
                            </table>
                            
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("order").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>