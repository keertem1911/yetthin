<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/bootstrap.css">
<style type="text/css">
table {
	margin-top: 10%;
	font-weight: bold;
	text-align: center;
	margin-left: 10%;
	margin-right: 20%;
}

tr td {
	height: 50px;
	font-size: medium;
}
</style>
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
<script type="text/javascript">
function deleteUse(){
	var url = window.location.href;
	url = url.substr(0, url.lastIndexOf("/"));
	url = url.substr(0, url.lastIndexOf("/") + 1);
	var msg = "您真的确定要删除吗？"; 
	var useId = $("#delete").attr("class");
	if (confirm(msg)==true){
		$.ajax({
			url:url+"/admin/deleteUserById/"+useId, 
			type:"DELETE",
			data:{"id":useId},
			dataType:"json",
			success:function(i){
				
					alert("删除成功");
					$("#delete").parent().parent().remove();
				
			},
		 });
	}else{ 
		  return false; 
	 } 
			  
};
	$(function() {

		var url = window.location.href;
		url = url.substr(0, url.lastIndexOf("/"));
		url = url.substr(0, url.lastIndexOf("/") + 1);
		$("#user_list tr").remove();
		$.ajax({
			url:url+"/admin/showUserinfo",
			type:"get",
			dataType:"json",
			success:function(lists){
				if(lists != null){
					for(var i=0;i<lists.length;i++){
						var user=lists[i];
						if(user.userRegisterTime == undefined){
							user.userRegisterTime = "";
						}				
						if(user.userIncomeTotle == undefined){
							user.userIncomeTotle = "";
						}						
						var s_tr=' ';
							s_tr+='<tr>';
							s_tr+=	'<td><span>'+user.userId+'</span></td>'
							s_tr+=	'<td><span>'+user.userPhone +'</span></td>';
							s_tr+=	'<td><span>'+user.userName+'</span></td>';
							s_tr+=	'<td><span>'+user.userRegisterTime+'</span></td>';
							s_tr+=	'<td><span>'+user.userIncomeTotle+'</span></td>';
							s_tr+=	'<td><span>'+user.email+'</span></td>';
							s_tr+=	'<td><a  id="detail" href="'+url+'/admin/selectUserDetailById/'+user.userId+'">';
							s_tr+=	'<span	class="label label-info">detail</span></a></td>';
							s_tr+=	'<td><a id="delete" a href="#"  onclick=deleteUse();  return false; class="'+user.userId+'" >';
							s_tr+=	'<span class="label label-warning">delete</span></a></td>';
							s_tr+='</tr>';
							$("#user_list").append(s_tr);				
					}
				}			
			},
			error:function(){
					alert("加载笔记失败");
				}
		});		
	})
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
	<div class="container formdiv">
	
	<form method="post">
		<input type="hidden" name="_method" value="delete" />
	</form>
	<table class=" table table-hover table-bordered table-condensed" border="1" bordercolor="black"
		cellpadding="1" cellspacing="1" style="width:500px">
		<thead>
			<tr>

				<td><span>userId</span></td>
				<td><span>userPhone</span></td>
				<td><span>userName</span></td>
				<td><span>userRegisterTime</span></td>
				<td><span>userIncomeTotle</span></td>
				<td><span>email</span></td>
				<td><span>详情</span></td>
				<td><span>删除</span></td>
			</tr>
		</thead>
		<tbody id="user_list">
		
		</tbody>
	</table>
 	</div>
	
</body>
</html>