<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/bootstrap.css">
<style type="text/css">
		 table{
	 	margin-top: 20px;
	 	 font-weight: bold;
	 	 	text-align: center;
	 }
	 tr td{
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
			$(function(){
				
				var url =window.location.href;
				 url=url.substr(0, url.lastIndexOf("/"));
				 url=url.substr(0, url.lastIndexOf("/")+1);
				  
				  $("a").click(function(){
					  var url=$(this).attr("href");
					  var thiz=this;
					$.post(url,{"_method":"delete"},function(msg){
						if(msg!=0){
							alert("删除成功");
							$(thiz).parent().parent().remove();
						}
						
					});
					  return false;
				  });
			 
			})
			
	</script>
</head>
<body>
	<form method="post"><input type="hidden" name="_method" value="delete"/></form>
<table class=" table table-hover" border="1" bordercolor="black" cellpadding="1" cellspacing="1">
				<thead>
				<tr>
					<td><span>userID</span></td>
					<td><span>userNum</span></td>
					<td><span>myMoney</span></td>
					<td><span>email</span></td>
					<td><span>password</span></td>
					<td><span>jpushID</span></td>
					<td><span>status</span></td>
					<td><span>jpushType</span></td>
					<td><span>删除</span></td>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="user" items="${requestScope.users }">
				<tr>
					<td><span>${user.userId }</span></td>
					<td><span>${user.phoneNum }</span></td>
					<td><span>${user.myMoney }</span></td>
					<td><span>${user.email }</span></td>
					<td><span>${user.password }</span></td>
					<td><span>${user.jpushId }</span></td>
					<td><span>${user.jpushStatus }</span></td>
					<td><span>${user.jpushType }</span></td>
					<td><a href="${pageContext.request.contextPath }/admin/deleteUserById/${user.userId}"><span class="label label-warning">delete</span></a></td>
				</tr>
				 
				</c:forEach>
				</tbody>
			</table>
</body>
</html>