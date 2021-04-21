<%@page import="model.Producer"%>
<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Thêm sản phẩm</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
      		  @SuppressWarnings("unchecked")
        	List<Category> categories = (List<Category>)request.getAttribute("categories");
     		   @SuppressWarnings("unchecked")
      		List<Producer> producer = (List<Producer>)request.getAttribute("producer");
     		   
     		   String name = request.getParameter("name");
     		   String cat_id = request.getParameter("category");
     		   String producer_id = request.getParameter("producer");
     		   String picture = request.getParameter("picture");
    		   String price = request.getParameter("price");
    		   String old_price = request.getParameter("old_price");
    		   String detail = request.getParameter("detail");
    		   
     		  String err= request.getParameter("err");
          	String msg= request.getParameter("msg");
          	
          	if("1".equals(err)){
          		%>
          		<div class='alert alert-warning' role='alert'>
        				  Thêm thất bại!!!
        				  </div>"
          	<% }if("1".equals(msg)){ %>
          		<div class="alert alert-primary" role="alert">
          				  Thêm thành công!!!
          				  </div>
          	<%}%>
          
        
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form role="form" method="post" enctype="multipart/form-data" id="form">
                                    <div class="form-group">
                                        <label for="name">Tên sản phẩm</label>
                                        <input type="text" id="name" value="<%if(name != null) out.print(name); %>" name="name" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="category">Danh mục sản phẩm</label>
                                        <select id="category" name="category" class="form-control">
                                        <%
                                        	if(categories != null && categories.size() > 0){
                                        		for(Category item: categories){
                                        %>
	                                        <option <%if(cat_id != null && cat_id.equals(String.valueOf(item.getId()))) out.print(" selected "); %> value="<%=item.getId()%>"><%=item.getName() %></option>
											<%}} %>
                                        </select>
                                    </div>
                                     <div class="form-group">
                                        <label for="producer">Hãng sản xuất</label>
                                        <select id="producer" name="producer" class="form-control">
                                         <%
                                        	if(producer != null && producer.size() > 0){
                                        		for(Producer item: producer){
                                        %>
	                                        <option <%if(producer_id != null && producer_id.equals(String.valueOf(item.getId()))) out.print(" selected "); %> value="<%=item.getId() %>"><%=item.getName() %></option>
											
											<%}} %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="picture">Hình ảnh</label>
                                        <input type="file" name="picture" />
                                    </div>
                                   <div class="form-group">
                                        <label for="price">Giá</label>
                                        <input type="text" id="price" value="<%if(price != null) out.print(price); %>" name="price" class="form-control" />
                                    </div>
                                       <div class="form-group">
                                        <label for="old_price">Giá cũ</label>
                                        <input type="text" id="old_price" value="<%if(old_price != null) out.print(old_price); %>" name="old_price" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="detail">Chi tiết</label>
                                        <textarea id="detail" class="form-control" id="detail" rows="5" name="detail"><%if(detail != null) out.print(detail); %></textarea>
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
    document.getElementById("product").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>