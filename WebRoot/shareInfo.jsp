<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>仓位调整接口</title>
</head>
<body>
	<lable>获取组合仓位信息</lable>
	<form action="${pageContext.request.contextPath}/share/getShareInfoByGroupId" method="post">
		 <input type="text" name="groupId"></input> 
		<input type="submit" value="submit"/>
	</form>
	<br/>
	<lable><font color="red">保存仓位</font></lable>
	<form action="${pageContext.request.contextPath}/share/changeShare" method="post">
		 <lable>组合Id</lable>
		 <input type="text" name="groupId"></input>
		 <label>股票列表  与保存组合格式一样<br/>  
		 	新增 列名   stockStatus (状态 0 为不变 1为 修改 2为新增 3为删除)   
		   常规   stockCode stockRatio stockName</label> <br/>
		   <label>代码集合</label>
		 <input type="text" name="stocks"></input> 
		<input type="submit" value="submit"/>
	</form>
	<br/>
	<br/>
	<lable><font color="red">查看组合调仓历史记录showShareChangeDate</font></lable>
	<form action="${pageContext.request.contextPath}/share/showShareChangeDate" method="post">
		 <lable>组合Id</lable>
		 <input type="text" name="groupId"></input>
		 <lable>用户Id</lable>
		 <input type="text" name="userId"></input> 
		<input type="submit" value="submit"/>
	</form>
	<br/>
</body>
</html>