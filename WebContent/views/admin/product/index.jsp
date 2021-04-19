<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý sản phẩm</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
        String err= request.getParameter("err");
      	String msg= request.getParameter("msg");
      	
      	if("1".equals(err)){
      		%>
      		<div class='alert alert-warning' role='alert'>
    				  Danh mục không tồn tại!!!
    				  </div>"
      	<% }if("1".equals(msg)){ %>
      		<div class="alert alert-primary" role="alert">
      				  Thêm thành công!!!
      				  </div>
      	<%}if("3".equals(err)){ %>
      	
      		<div class="alert alert-warning" role="alert">
      				Có lỗi khi XÓA!!
      				  </div>
      	<%}if("5".equals(err)){ %>
      	
  		<div class="alert alert-warning" role="alert">
  				 ID không tồn tại!!
  				  </div>
  		<%} if("3".equals(msg)){%>
  		<div class="alert alert-warning" role="alert">
  				 Xóa thành công!
  				  </div>
  		<%}if("2".equals(msg)){%>
  		<div class="alert alert-warning" role="alert">
  				Sửa thành công!!
  				  </div>
  		<%}%>
        
       
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div class="row">
                                <div class="col-sm-6">
                                    <a href="<%=request.getContextPath()%>/admin/product/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" class="form-control input-sm" placeholder="Nhập tên sản phẩm" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Danh mục sản phẩm</th>
                                        <th>Hãng sản xuất</th>
                                        <th>Hình ảnh</th>
                                        <th>Giá mới</th>
                                        <th width ="100px">Giá cũ</th>
                                        <th>Ngày tháng</th>
                                        <th>Lượt xem: </th>
                                        <th>chi tiết</th>
                                        <th width="50px">Chức năng</th>
                                    </tr>
                                </thead>
                                	
                                <tbody>
                                <%
                                	List<Product> products =(List) request.getAttribute("products");
                                if(products != null && products.size()>0){
                                	for(Product items : products){
                                %>
                                    <tr>
                                        <td><%=items.getId() %></td>
                                        <td class="center"><%=items.getName() %></td>
                                        <td class="center"><%=items.getCategory().getName() %></td>
                                        <td class="center"><%=items.getProducer().getName() %></td>
                                        <td class="center">
                                        <%if(items.getPicture().isEmpty()){ %>
                                        Không có hình ảnh
                                        <%}else{ %>
											<img width="100px" height="100px" src="<%=request.getContextPath() %>/uploads/<%=items.getPicture() %> " alt="<%=items.getName() %>"/>
											<%} %>
                                        </td>
                                        <td class="center"><%=items.getPrice() %> vnđ</td>
                                        <td class="center"><%=items.getOld_price()%> vnđ</td>
                                        <td class="center"><%=items.getDate_create() %></td>
                                        <td class="center"><%=items.getCounter() %></td>
                                        <td class="center"><%=items.getDetail() %></td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath()%>/admin/product/edit?id=<%=items.getId() %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=request.getContextPath()%>/admin/product/del?id=<%=items.getId() %>" title="" onclick="return confirm('Bạn có chắc muốn xóa?')" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
									<%}}else{ %>
									<tr>
										<td colspan="10" align = "center">Không có sản phẩm!!!</td>
									</tr>
									<%} %>
                                </tbody>
                            </table>
                            <%
                            int numberOfItems =(Integer) request.getAttribute("numberOfItems");
                        	int numberOfPage =(Integer) request.getAttribute("numberOfPage");
                       		int currentPage =(Integer) request.getAttribute("currentPage");
                       			if(products != null && products.size() >0){
                            %>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ trang 1 đến trang <%=numberOfPage %> của <%=numberOfItems %> sản phẩm</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/product/index?page=<%if(currentPage == numberOfPage){out.print(numberOfPage);}else{out.print(currentPage -1);}%>">Trang trước</a></li>
                                             <% 
                                            	String active = "";
                                            for(int i=1; i<=numberOfPage; i++){
                                            	if(currentPage == i){
                                            		active = "active";
                                            	}else{
                                            		active = "";
                                            	}
                                            	%>
                                            <li class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/product/index?page=<%=i%>"><%=i %></a></li>
                                           <%} %>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/product/index?page=<%if(currentPage == numberOfPage){out.print(numberOfPage);}else{out.print(currentPage +1);}%>">Trang tiếp</a></li>
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
    document.getElementById("product").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>