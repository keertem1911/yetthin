<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-form.js"></script>
<script type="text/javascript">
 
</script>
</head>
<body>
	<form  id="form" action="${pageContext.request.contextPath }/uploadInterface" 
	enctype="multipart/form-data"	method="post" >
		<label>上传接口文档exl</label>.
		<input type="file" name="requestIntface"><br/>
		<input type="submit" value="submit"/>
	</form>
</body>
</html>