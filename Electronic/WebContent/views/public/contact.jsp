<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/templates/public/inc/header.jsp" %>

    <!-- end of menu tab -->
    <div class="crumb_navigation"> Navigation: <a href="#">Home</a> &lt; <span class="current">Category name</span> </div>
   <%@ include file="/templates/public/inc/leftbar.jsp" %>
   
    <!-- end of left content -->
    
     <%
      	String msg = request.getParameter("msg");
      if("success".equals(msg)){
    	  out.print("<div style=\"background: yellow; color: red; font-weight: bold; padding: 5px; text-align: center; margin-left: 200px; \">gửi liên hệ thành công</div>");
      }
      if("error".equals(msg)){
    	  out.print("<div style=\"background: yellow; color: red; font-weight: bold; padding: 5px; text-align: center; margin-left: 200px; \">gửi liên hệ thất bại</div>");
      }
     	String name = request.getParameter("name");
		String email = request.getParameter("email");
		String num_phone = request.getParameter("num_phone");
		String address = request.getParameter("address");
		String message = request.getParameter("message");
      
      %>
    <div class="center_content">
      <div class="center_title_bar">Contact Us</div>
      
      <div class="prod_box_big">
      <p style="font-size: 13px; color: red; margin-bottom: 15px;">Đối với những thắc mắc và câu hỏi, xin hãy điền các thông tin cần thiết vào form bên dưới và gửi cho chúng tôi. 
      Chúng tôi sẽ phản hồi sớm nhất có thể. cảm ơn !!!</p>
        <div class="top_prod_box_big"></div>
        <div class="center_prod_box_big">
          <div class="contact_form">
           <form action="" method="post" id="contact_form" class="contact_form">
            <div class="form_row">
              <label class="contact"><strong>Name:</strong></label>
              <input type="text" name="name" class="contact_input" />
            </div>
            <div class="form_row">
              <label class="contact"><strong>Email:</strong></label>
              <input type="text" name="email" class="contact_input" />
            </div>
            <div class="form_row">
              <label class="contact"><strong>Phone:</strong></label>
              <input type="text" name="num_phone" class="contact_input" />
            </div>
            <div class="form_row">
              <label class="contact"><strong>Address:</strong></label>
              <input type="text" name="address" class="contact_input" />
            </div>
            <div class="form_row">
              <label class="contact"><strong>Message:</strong></label>
              <textarea class="contact_textarea" rows="8" cols="50" name="message"></textarea>
            </div>
            <%-- <div class="form_row"> <a href="<%=request.getContextPath() %>/contact" class="contact">send</a> </div> --%>
           <div class="form_row">
           <input type="submit" name="imageField" id="imageField"  class="contact" />
           </div>
            
            </form>
          </div>
        </div>
        <div class="bottom_prod_box_big"></div>
      </div>
    </div>
      <script type="text/javascript" class="vali" >
	$(document).ready(function () {
		$('#contact_form').validate({
			rules: {
				"name": {
					required: true,
					minlength : 8,
					maxlength : 32,
				},
				"email": {
					required: true,
					email: "true",
				},
				"phone": {
					required: true,
				},
				"address": {
					required: true,
				},
				"message": {
					required: true,
				},
			},
			messages: {
			  "name": {
				required : "Vui lòng nhập Tên",
				minlength : "Nhập tối thiểu 8 kí tự",
				maxlength : "nhập tối đa 32 kí tự",
			  },
			  "email": {
				required: "vui lòng nhập email",
				email: "Vui lòng nhập đúng định dạng",
			  },
			  "phone": {
				required: "Vui lòng nhập số điện thoại",
			  },
			  "address": {
					required: "Vui lòng nhập địa chỉ",
				  },
			  "message": {
						required: "Vui lòng nhập mô tả",
					  },
			},
		});
	});	
</script>
    <!-- end of center content -->
      <%@ include file="/templates/public/inc/rightbar.jsp" %>
    <!-- end of right content -->
  </div>
  <!-- end of main content -->
 <%@ include file="/templates/public/inc/footer.jsp" %>
