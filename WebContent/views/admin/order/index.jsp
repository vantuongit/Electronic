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
                            <%
                            		String msg = request.getParameter("msg");
                            		String err = request.getParameter("err");
                            		if(GlobalConstant.SUCCESS.equals(msg)){
                            %>
                            	<h4 class= "SUCCESS"> XỬ LÝ THÀNH CÔNG!</h4>
                            <%
                            		}%>
                           <%
                           		if("1".equals(err)){
                           %>
                          <div class="alert alert-warning" role="alert">
  								Trùng tên!!!
								</div>
                           <%} %>
							
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Phone</th>
                                        <th>Address</th>
                                        <th>Status</th>
                                        <th width="100px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                @SuppressWarnings("unchecked")
                                	List<Order> order = (List<Order>)request.getAttribute("listOrder");
                                	
                                if(order != null && order.size()>0 ){
                                	for(Order orders :order){
                                		int id = orders.getId();
                                		String name = orders.getMember().getFullname();
                                		String phone = orders.getMember().getPhone();
                                		String address= orders.getMember().getAddress();
                                		String urlShow = request.getContextPath()+"/show-order?id="+id;
                                		String urlDel = request.getContextPath()+"/admin/order/del?id="+id;
                                %>
                                    <tr>
                                        <td><%=id %></td>
                                        <td class="center"><%=name %></td>
                                        <td class="center"><%=phone %></td>
                                        <td class="center"><%=address %></td>
                                        <td class="center">
                                        	<a href="<%=request.getContextPath() %>/status"><input type="checkbox" /></a>
                                        </td>
                                        <td class="center">
                                            <a href="<%=urlShow %>" title="xem" class="btn btn-primary"><i class="fa fa-edit "></i> Xem</a>
                                            <%-- <a href="<%=urlDel %>" title="Xóa"  onclick="return confirm('bạn có chắc muốn xóa?')" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a> --%>
                                        </td>
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