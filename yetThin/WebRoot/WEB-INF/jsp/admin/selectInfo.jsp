<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/bootstrap.css">
	
<style type="text/css">
 	 
		 table{
	 	margin-top: 20px;
	 	margin-top: 20px;
	 	 font-weight: bold;
	 	 	text-align: center;
	 }
	 tr td{
	 	height: 50px;
	 	font-size: medium;
	 
	 }
 
</style>
<title>个人信息查询</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
</head>
<body>
	 
	<table class=" table table-hover" border="1" bordercolor="black" cellpadding="1" cellspacing="1">
				<thead>
				<tr>
					<td><span>adminID</span></td>
					<td><span>adminName</span></td>
					<td><span>ideaText</span></td>
				</tr>
				</thead>
				<tbody >
				<c:forEach var="user" items="${requestScope.users }">
				<tr>
					<td><span>${user.userId }</span></td>
					<td><span>${user.phoneNum }</span></td>
					<td><span>${user.myMoney }</span></td>
					<td><span>${user.email }</span></td>
					<td><span>${user.password }</span></td>
					<td><span>${user.jpushId }</span></td>
					<td><span>${user.status }</span></td>
					<td><a href="#"><span class="label label-warning">delete</span></a></td>
				</tr>
				 
				</c:forEach>
				</tbody>
			</table>
</body>
</html>