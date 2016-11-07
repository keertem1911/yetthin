<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/bootstrap.css">
<style type="text/css">
body{
	background-color:  #333333;
}
.header {
	margin-top: 0px;
	text-align: center;
}

.login {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 40%;
	height: 60%;
	background-color: #666666 ;
}
.form{
	margin-top: 20px;
	width: 50%;
	position: absolute;
	top: 40%;
	left: 50%;
	transform: translate(-50%, -50%);
	height: 80%;
	 
}
.formtalbe{
	margin-top: 30%;
}
.form input {
		width: 70%;
		height: 30px;
}
  .formheader{
position:absolute;
	margin-top: 10px;
	text-align: center;
}
 .navbar-text{
 	font-size: x-large;
 }
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
<script type="text/javascript">
	$(function(){
		function init(){
			var status=<%=session.getAttribute("status")%>
			 
			if(status!=null&&status<1){
				<% session.setAttribute("status", null); %>
				if(status==-1)
				alert("密码错误");
				else
					alert("登陆错误");
			}
			}
		init();
		$(".formsubmit").click(function(){
			var data={};
			var url=$(".formtalbe1").attr("action");
			var obj=$(".formtalbe1").find(":input");
			 
			for(var i=0;i<obj.length;++i){
				var types=obj[i].type;
				 
				  if(types!="submit"){
				// console.log(obj[i].name+"="+obj[i].value);
					  if(obj[i].name!="")
				 	data[obj[i].name]=obj[i].value.trim();
				}  
			}
			 
		 	  $.post(url,data,function(msg){ 
		 		 
				 
		 		  if(msg.status==200){
						alert("注册成功");
		 		 $('#myModal').modal('hide');
		 		  }
		 		  else{
		 			  alert(json["status"]);
		 		  }
					
		 	  });
			
		});
	})
</script>
</head>
<body>
 
	<nav class="navbar navbar-inverse">
		 
		<p class="navbar-text">后台登陆中心</p>
		</nav>
	 
	<div class="containe row   login">
	<div class="form">
		<h1 class="formheader">Login</h1>
		<form class="formtalbe"  action="${pageContext.request.contextPath }/admin/login" method="post">
			<div class="form-group">
				 <label>Admin Name</label><br/> 
   				 <input type="text" name="adminName" placeholder=" 请输入用户名"/>
				<% if(session.getAttribute("adminLogin")!=null) { 
				%>
			<label style="color: red">登陆失败!!</label>
				<%} %>
			</div>
			<div class="form-group">
				 <label>Password</label><br/>
   				 <input type="password" name="adminPassword" placeholder=" 请输入密码"/>
			</div>
			<!-- <div class="form-group">
				<label for="exampleInputFile">File input</label> <input type="file"
					id="exampleInputFile">
				<p class="help-block">Example block-level help text here.</p>
			</div> -->
			 
			<button type="submit" class="btn btn-default ">Submit</button> 
				<!-- Button trigger modal -->
			  	<button type="button" class="btn btn-primary btn-lg" data-whatever="@mdo" data-toggle="modal"
				 data-target="#myModal">
 					 Register
				</button>  	
		</form>
</div>
	</div>


<div class="modal fade" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content col-md-6 col-md-offset-3">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
         
      </div>
      <div class="modal-body ">
         <h3>Register</h3>
		<form class="formtalbe1" action="${pageContext.request.contextPath }/admin/register" method="post">
			
			<div class="form-group">
				 <label>Admin Name</label><br/> 
   				 <input type="text" name="adminName" placeholder=" 请输入用户名"/>
			</div>
			<div class="form-group">
				 <label>Password</label><br/>
   				 <input type="password" name="adminPassword" placeholder=" 请输入密码"/>
			</div>
			<!-- <div class="form-group">
				 <label>Rept Password</label><br/>
   				 <input type="password" name="reptadminPassword" placeholder=" 请输入密码"/>
			</div> -->
			<!-- <div class="form-group">
				<label for="exampleInputFile">File input</label> <input type="file"
					id="exampleInputFile">
				<p class="help-block">Example block-level help text here.</p>
			</div> -->
			 <input type="hidden" name="_method" value="put"/>
			<button type="button" class="btn btn-default formsubmit">Submit</button>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</body>
</html>