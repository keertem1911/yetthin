<%@page import="com.yetthin.web.domain.PhoneVersion"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/bootstrap.css">
<style type="text/css">
	 .word{
	 	color:black;
	 }
	 .main{
	 margin-top: 20px;
	 }
	  
</style>
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-form.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#form2").submit(function(){
					   var url=$(this).attr("action");
					  var mapping ={
								url:url,
								dataType:"json",
								type:"post",
								contentType: "application/json; charset=utf-8", 
								data:{
									//"versionName":$("#versionName").val(),
									//"explain":$("#explain").val(),
								//	"file":$("#fileApk").val(),
								//	"versionCode":$("#versionCode").val(),
								//	"apkUrl":"",
									"_method":"put"
									},
								success:function(msg){
									if(msg==200){
									alert("保存成功");
								//	$("#showlist").load($("#showurl").val());
									}
									else
									alert("保存失败");
								}
						};
					  $("#form2").ajaxSubmit(mapping);	
					  return false;
				 
			})
		 
		 
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
	<div style=" margin-top:60px">
 
		 	<!-- Tab panes -->
		 
			<label>版本号</label>
			<table class="table"  >
				<thead class="table-striped">
					<tr><td>版本号</td><td>版本名称</td><td>apk_url</td><td>说明</td></tr>
				</thead>
				<tbody class="table-bordered">
					<% 
						List<PhoneVersion> phones= (List<PhoneVersion>)session.getAttribute("phones"); 
						if(phones!=null)
						for(PhoneVersion v:phones){
						%>
						<tr>
							<td><%=v.getVersionCode() %></td>
							<td><%=v.getVersionName() %></td>
							<td><a href="<%=v.getApkUrl() %>"><%=v.getApkUrl() %>下载</a></td>
							<td><%=v.getExplain() %></td>
						</tr>
					<%} %>
				</tbody>
			</table>
				<form id="form2" action="${pageContext.request.contextPath}/admin/updateNewVersiontext" 
				enctype="multipart/form-data" method="post">
				 		<div class="form-group">
						<label>版本号</label> <input type="text" class="form-control"
							name="versionCode" id="versionCode" placeholder="versionCode" />
					</div>
					<div class="form-group">
						<label>版本名称</label> <input type="text" class="form-control"
							name="versionName" id="versionName" placeholder="versionName" />
					</div>
				 
					<div class="form-group">
						<label>apk说明</label> <input type="text" class="form-control"
							name="explain" id="explain" placeholder="explain" />
					</div>
					<br />
				 	<div class="form-group">
				 		 <label >上传apk更新文件</label> 
						<input type="file" id="fileApk" name="file"/>
					</div>
					 
					<button type="submit" id="dophoneversion" class="btn btn-default">Submit</button>
				</form>
		 

	</div>

	 
</body>
</html>