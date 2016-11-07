<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/bootstrap.css">
<style type="text/css">
	 form{
	 	position:absolute;
	 	left:40%;
	 	top:50%;
	 	margin-top: 20px;
	 	font-size: x-large;
	 }
	 
</style>
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
	<script type="text/javascript">
		$(function(){
			 
			$("#formdiv").click(function(){
				var href=$(".formmain").attr("action");
				$.post(href,{_method:"put",password:$("#exampleInputPassword1").val(),adminName:$("#adminName").val()},
						function(msg){
					if(msg.status=="200")
						{
							alert("更改成功!");
					$("#adminName").val("");
					$("#exampleInputPassword1").val("");
						}else{
							alert(msg.status);
							$("#adminName").focus();
						}
				})
			});
		})
	</script>
</head>
<body>
	 <div class="col-lg-8">
	 <form action="${pageContext.request.contextPath }/admin/changeAdminname" class="formmain">
  <div class="form-group">
    <label  >新用户名</label>
    <input type="text" class="form-control" name="adminName"id="adminName" placeholder="Password">
  </div>
  <div class="form-group">
    <label  >密码</label>
    <input type="password" class="form-control" name="password"id="exampleInputPassword1" placeholder="Password">
  </div>
  
  <button type="button" id="formdiv" class="btn btn-default">Submit</button>
</form>
</div>
</body>
</html>