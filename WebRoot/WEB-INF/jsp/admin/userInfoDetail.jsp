<%@page import="com.yetthin.web.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/bootstrap.css">

<title></title>
<style>
	 #outer{
	 	margin-top: 50px;
	 }
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
<script type="text/javascript">
	$(function(){
		var url =window.location.href;
		 url=url.substr(0, url.lastIndexOf("/"));
		 url=url.substr(0, url.lastIndexOf("/")+1);	

		 $("#update").click(function(){
			 var msg = "您真的确定要更改吗？\n\n请确认！"; 
			 if (confirm(msg)==true){ 
							userId=$("#userId").val().trim();
						 	userPhone=$("#userPhone").val().trim();
						 	userName=$("#userName").val().trim();
						 	userFirm=$("#userFirm").val().trim();
						 	userVipFlag=$("#userVipFlag").val().trim();
						 	email=$("#email").val().trim();
						 	ideaText=$("#ideaText").val().trim();
			 	$.ajax({
			 		url:url+"admin/updateUserInfo",	
			 		type:"post",
			 		data:{"userId":userId,"userPhone":userPhone,"userName":userName,
			 				"userFirm":userFirm,"userVipFlag":userVipFlag,"email":email,"ideaText":ideaText},
			 		
			 		dataType:"json",
					success:function(m){
						
						if(m.status==200){
							alert(m.info);
						}
					}
			 	});
			 }else{ 
			  return false; 
			 } 
		 });
	});
</script>
</head>
<body>
<nav class="navbar navbar-inverse   navbar-fixed-top ">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <!-- 商标 图片 -->
      <a class="navbar-brand" href="#">
      	<img alt="Brand" src=""/></a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1 ">
      <ul class="nav navbar-nav">
        <li ><a href="${pageContext.request.contextPath }/pageRequest/home" class="indexhref">首页<span class="sr-only">(current)</span></a></li>
       <!--  <li><a href="#">Link2</a></li>
       -->  
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
           aria-expanded="false">客户端用户管理 <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="${pageContext.request.contextPath }/pageRequest/showUserinfo"   >查看全部用户信息</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="${pageContext.request.contextPath }/pageRequest/selectByphoneNum" >通过电话号查看用户信息</a></li>

          </ul>
        </li>
      </ul>
    <!--   <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form> -->
      <ul class="nav navbar-nav navbar-left">
        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
          aria-haspopup="true" aria-expanded="false">后台管理<span class="caret"></span></a>
          <ul class="dropdown-menu">
           <li><a href="${pageContext.request.contextPath }/pageRequest/showRiskMinitor" >风控监控</a></li>
         
            <li><a href="${pageContext.request.contextPath }/pageRequest/updateNewVersion">上传Andriod新版本</a></li>
            <li><a href="${pageContext.request.contextPath }/pageRequest/updateHeadPicture" >上传头部图片</a></li>
            <li><a href="${pageContext.request.contextPath }/pageRequest/updateInterface">上传接口文档</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="${pageContext.request.contextPath }/pageRequest/lookfeedback">查看反馈</a></li>
             
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
          aria-haspopup="true" aria-expanded="false">个人信息管理<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="${pageContext.request.contextPath }/pageRequest/changePwd" >更改密码</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="${pageContext.request.contextPath }/pageRequest/changeUsername">更改用户名</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="${pageContext.request.contextPath }/pageRequest/selectInfo">查询信息</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="${pageContext.request.contextPath }/pageRequest/selectAllAdmin">查询所有管理员</a></li>
          </ul>
        </li>
       <!--  <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
          aria-haspopup="true" aria-expanded="false">Dropdown4 <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action4</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li> -->
      </ul>
      <ul class="nav navbar-nav navbar-right">
      	<li><a href="javascript:void(0);"><span class="label label-info">管理员</span></a></li>
      	<li><a href="${pageContext.request.contextPath }/admin/logout" class="logout"><span class="label label-danger">退出登录</span></a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
	<% User user =(User)request.getAttribute("user");
	%>
	<div id="outer" style="margin-left: auto;margin-right: auto;text-align: center;">
	<form class="form-horizontal" class="text-center" role="form">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">userId</label>
    <div class="col-sm-3">
       <input  type="email"  class="form-control" id="userId" value="<%=user.getUserId() %>" readonly >
    </div>
  </div>
   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">userPhone</label>
    <div class="col-sm-3">
      <input  type="email"  class="form-control" id="userPhone" value="<%=user.getUserPhone() %>"  >
    </div>
  </div>
   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">userName</label>
    <div class="col-sm-3">
      <input type="email"   class="form-control" id="userName" value="<%=user.getUserName() %>" >
    </div>
  </div>
   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">userPassword</label>
    <div class="col-sm-3">
      <input type="email"  class="form-control" id="userPassword" value="<%=user.getUserPassword() %>" readonly>
    </div>
  </div>
   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">userFirm</label>
    <div class="col-sm-3">
      <input type="email"  class="form-control" id="userFirm" value="<%=user.getUserFirm() %>" >
    </div>
  </div>
   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">userVipFlag</label>
    <div class="col-sm-3">
      <input type="email"  class="form-control" id="userVipFlag" value="<%=user.getUserVipFlag() %>"  >
    </div>
  </div>
   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">userRegisterTime</label>
    <div class="col-sm-3">
      <input type="email"  class="form-control" id="userRegisterTime" value="<%=user.getUserRegisterTime() %>" readonly>
    </div>
  </div>
   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">userTradePassword</label>
    <div class="col-sm-3">
      <input type="email"  class="form-control" id="userTradePassword"  value="<%=user.getUserTradePassword() %>"readonly>
    </div>
  </div>
   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">userCommuniPassword</label>
    <div class="col-sm-3">
      <input type="email"  class="form-control" id="userCommuniPassword"  value="<%=user.getUserCommuniPassword() %>"readonly>
    </div>
  </div>
   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">userIncomeTotle</label>
    <div class="col-sm-3">
      <input type="email"  class="form-control" id="userIncomeTotle"  value="<%=user.getUserIncomeTotle() %>" readonly>
    </div>
  </div>
   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">userCommuniPassword</label>
    <div class="col-sm-3">
      <input type="email"  class="form-control" id="userCommuniPassword"  value="<%=user.getUserCommuniPassword() %>"readonly >
    </div>
  </div>
   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">email</label>
    <div class="col-sm-3">
      <input type="email"  class="form-control" id="email" value="<%=user.getEmail() %>" >
    </div>
  </div>
   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">jpushId</label>
    <div class="col-sm-3">
      <input type="email"  class="form-control" id="jpushId" value="<%=user.getJpushId() %>"readonly>
    </div>
  </div>
   
   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">jpushStatus</label>
    <div class="col-sm-3">
      <input type="email"  class="form-control" id="jpushStatus"  value="<%=user.getJpushStatus() %>"readonly>
    </div>
  </div>
   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">ideaText</label>
    <div class="col-sm-3">
      <input type="email"  class="form-control" id="ideaText" value="<%=user.getIdeaText() %>" >
    </div>
  </div>
   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">jpushType</label>
    <div class="col-sm-3">
      <input type="email"  class="form-control" id="jpushType" value="<%=user.getJpushType() %>"readonly >
    </div>
  </div>
   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">emailStatus</label>
    <div class="col-sm-3">
      <input type="email"   class="form-control" id="emailStatus" value="<%=user.getEmailStatus() %>"readonly>
    </div>
  </div>
    <div class="form-group">
    <div class="col-sm-offset-2 col-sm-3">
      <button type="submit"  class="btn btn-danger  btn-lg" id="update">修改</button>
    </div>
  </div>
    <div class="form-group">
    <div class="col-sm-offset-2 col-sm-3">
       <a  href="javascript:history.back();" class="btn btn-primary btn-sm">返回</a>
    </div>
  </div>
</form>
  </div>
		 
			 
</body>
</html>