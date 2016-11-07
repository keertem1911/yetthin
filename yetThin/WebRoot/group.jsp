<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<label>标的类型</label>
	<form action="${pageContext.request.contextPath }/group/stockType" method="post">
		 
		<input type="submit" value="submit"/>
	</form>
	<br/>
	<label>标的版块详情</label>
	<form action="${pageContext.request.contextPath }/group/stockTypeList" method="post">
		<label>标的类型编号</label>
		<select   name="stockType">
			<option value="0">股票板块</option>
			<option value="1">期货品种</option>
			<option value="2">期权品种</option>
		</select>
		
		<input type="submit" value="submit"/>
	</form>
	<br/>
	<label>组合标的清单</label>
	<form action="${pageContext.request.contextPath }/group/stockofgroup" method="post">
		<label>已选版块</label>
		<input type="text" name="selectedModels"/><br>
		<label>策略类型</label>
		<input type="text" name="strategyType"/><br>
		<label>策略编号</label>
		<input type="text" name="strategyID"/><br>
		<label>投资额度</label>
		<input type="text" name="investCap"/><br>
		<label>股票数量</label>
		<input type="text" name="stockCount"/><br>
		<label>投资周期</label>
		<input type="text" name="investTime"/><br>
		
		<input type="submit" value="submit"/>
	</form>
	<br/>
	
	<label>组合保存</label>
	<form action="${pageContext.request.contextPath }/group/stockofgroupSave" method="post">
		<label>组合名称</label>
		<input type="text" name="groupName"/><br>
		<label>创建者编号</label>
		<input type="text" name="userID"/><br>
		<label>初始资金</label>
		<input type="text" name="initMoney"/><br>
		<label>对应策略编号</label>
		<input type="text" name="strategyId"/><br>
		<label>参考指数</label>
		<input type="text" name="indexCode"/><br>
		<label>标的代码	</label>
		<input type="text" name="stockCode"/><br>
		<label>标的占比</label>
		<input type="text" name="stockRatio"/><br>
		
		<input type="submit" value="submit"/>
	</form>
	<br/>
		<label>组合综览</label>
	<form action="${pageContext.request.contextPath }/group/Summarize" method="post">
		<label>请求页数</label>
		<input type="text" name="pageNum"/><br>
		<label>页内数量</label>
		<input type="text" name="pageSize"/><br>
		<input type="submit" value="submit"/>
	</form>
</body>
</html>