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
	 .tbody-div tr td{
	 	height: 50px;
	 	font-size: medium;
	 
	 }
</style>
<title>Insert title here</title>
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
					<td><span>power</span></td>
				</tr>
				</thead>
				<tbody >
				 	<c:forEach items="${requestScope.admins }" var="admin">
							<tr>
					<td><span>${admin.id }</span></td>
					<td><span>${admin.adminName}</span></td>
					<td><span>${admin.adminPower}</span></td>
				</tr>				 		
				 		
				 	</c:forEach>
				</tbody>
			</table>
</body>
</html>