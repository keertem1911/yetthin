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
			$("#subbtn").click(function(){
				var url=$("#formdiv").attr("action");
				data={};
				var types=$("#formdiv").find(":input");
				for(var i=0;i<types.length;++i){
					if(types.type!="button"){
						if(types[i].name!=""){
							data[types[i].name]=types[i].value.trim();
						}
					}
				}
				data["_method"]="put";
				$.post(url,data,function(msg){
					if(msg=="200"){
						alert("密码修改成功");
						
					}else{
						alert("密码修改失败");
					}
				})
				
			})
			
		})
	</script>
</head>
<body>
	<div class="col-lg-8">
	 <form action="${pageContext.request.contextPath }/admin/changePwd" method="post" id="formdiv">
  <div class="form-group">
    <label for="exampleInputPassword1">old Password</label>
    <input type="password" class="form-control" name="oldPassword"id="oldPassword" placeholder="Password">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">new Password</label>
    <input type="password" class="form-control"  name="newPassword" id="newPassword" placeholder="Password">
  </div>
  
  <button type="button" id="subbtn"class="btn btn-default">Submit</button>
</form>
</div>
</body>
</html>