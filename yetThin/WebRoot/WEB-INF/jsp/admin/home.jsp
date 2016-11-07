<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/bootstrap.css">
<style type="text/css">
body { padding-top: 70px; 
 background-color: white;
}
.maindiv {
	background-color: white;
 	position: absolute;
 	top:6%;
 	height: auto;
 	width: 100%;
}
.formdiv{
	margin-top: 60px;
}
</style>
<title>admin home</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
	<script type="text/javascript">
		$(function(){
			var url =window.location.href;
			  url=url.substr(0, url.lastIndexOf("/"));
			  url=url.substr(0, url.lastIndexOf("/")+1);
		 
			  $(".formdiv").load(url+"pageRequest/indexhref");
			$("a").click(function(){
				var className=$(this).attr("class");

				if(className!="dropdown-toggle"&&className!="logout"){
					var pref="pageRequest/";
					if(className=="showUserinfo"||className=="lookfeedback"
					||className=="selectInfo"||className=="selectAllAdmin"){
						console.log("s");
						pref="admin/";	
					}
					
					$(".formdiv").load(url+pref+className);
					
				}
			});
			
			  
			//bootstrap的插件都需要激活。
			//加一行代码在JS里应该就可以了
			$('.dropdown-toggle').dropdown()
			
			 
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
        <li ><a href="#" class="indexhref">首页<span class="sr-only">(current)</span></a></li>
       <!--  <li><a href="#">Link2</a></li>
       -->  
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
           aria-expanded="false">客户端用户管理 <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="javascript:void(0);" class="showUserinfo"  >查看全部用户信息</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="javascript:void(0);" class="selectByphoneNum">通过电话号查看用户信息</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="javascript:void(0);" class="selectByEmail">通过邮箱号查看用户信息</a></li>
            
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
           <li><a href="javascript:void(0);" class="showRiskMinitor">风控监控</a></li>
         
            <li><a href="javascript:void(0);" class="updateNewVersion">上传Andriod新版本</a></li>
            <li><a href="javascript:void(0);" class="updateHeadPicture">上传头部图片</a></li>
            <li><a href="javascript:void(0);" class="updateInterface">上传接口文档</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="javascript:void(0);" class="lookfeedback">查看反馈</a></li>
             
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
          aria-haspopup="true" aria-expanded="false">个人信息管理<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="javascript:void(0);" class="changePwd">更改密码</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="javascript:void(0);" class="changeUsername">更改用户名</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="javascript:void(0);" class="selectInfo">查询信息</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="javascript:void(0);" class="selectAllAdmin">查询所有管理员</a></li>
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
	<div class="container-fluid maindiv">
	<div class="container formdiv">
			 
 	</div>
</body>
</html>