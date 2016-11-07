<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	form {
		vertical-align: center;
		text-align: left;
		margin-left: 20px;
		
	}
	.main{
	margin-top: 20px;
	}
</style>
<title>Insert title here jsp</title>
<script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="js/jquery-form.js"></script>

<script type="text/javascript">
 	 
</script>
</head>
<body>
	<div class="main">
 	<form action="${pageContext.request.contextPath }/user/register" method="post">
 	 	<label>register</label>
 	 	<input type="text" name="phoneNum" placeholder="电话号码"/>
 		<input type="password" name="password" placeholder="密码"/>
 		<!-- <input type="text" value="" name="verifyCode" placeholder="验证码"/>
 	 -->	
 		<input type="submit" value="submit"  />
 	</form>
 	<br/>
 <%-- 	<form action="${pageContext.request.contextPath }/user/getRegisterVerify" method="post">
 	 	<label>getRegisterVerify</label>
 		<input name="phoneNum" type="text" placeholder="电话号码"/>
 		<input type="submit" value="submit"/>
 	</form> --%>
 	<br/>
 	<form action="${pageContext.request.contextPath }/user/login" method="post">
 	 	<label>login</label>
 	 	<input type="text" name="phoneNum" placeholder="电话号码"/>
 		<input type="password" name="password" placeholder="密码"/>
 		<input type="submit" value="submit"  />
 	</form>
 	<br/>
 	<form action="${pageContext.request.contextPath }/user/forgetPwd" method="post">
 	 	<label>forgetPwd</label>
 		<input name="phoneNum" type="text" placeholder="电话号码"/>
 		 
 		<input name="password" type="password" placeholder="密码"/>
 		<input type="submit" value="submit"/>
 	</form><!-- 
 	<br/>
 	<form action="${pageContext.request.contextPath }/user/getforgetPwdVerify" method="post">
 	 	<label>getforgetPwdVerify</label>
 		<input name="phoneNum" type="text" placeholder="电话号码"/>
 		<input name="_method" type="hidden" value="put" />
 		<input type="submit" value="submit"/>
 	</form>  -->
 	<br/>
 	<form action="${pageContext.request.contextPath }/user/updateJPushID"  id="updateJpushIDForm" method="post">
 	
 	 	<label>updateJPushID</label>
 		<input name="userID" type="text" placeholder="用户Id" />
 		<input name="JpushID" type="text" placeholder="更新极光ID" />
 		<input name="_method" type="hidden" value="put" />
 		 
 		<select name="phoneType" >
 			<option value="0">推送极光的客户端</option>
 			<option value="1">Android</option>
 			<option value="-1">IOS</option>
 		</select>
 		
 		<input type="submit" value="submit"/>
 	</form>
 	<br/>
 	<form action="${pageContext.request.contextPath }/user/updateJPushStatus" method="post">
 	 	<label>updateJPushStatus</label>
 		<input name="userID" type="text" placeholder="用户ID"/>
 		<input name="JpushStatus" type="text" placeholder="推送极光的开关 1关闭 0打开" />
 		<input type="text" name="JpushType" placeholder="推送类型"/>
 		<input type="submit" value="submit"/>
 	</form>
 	<br/>
 	<form action="${pageContext.request.contextPath }/user/bindingEmail" method="post">
 	 	<label>bindingEmail</label>
 		<input name="userID" type="text" placeholder="用户ID"/>
 		<input name="email" type="text" placeholder="用户邮箱" />
 		<input name="_method" type="hidden" value="put" />
 		<input type="submit" value="submit"/>
 	</form>
 	<br/>
 	<form action="${pageContext.request.contextPath }/user/changePwd" method="post">
 	 	<label>changePwd</label>
 		<input name="userID" type="text" placeholder="用户ID"/>
 		<input name="oldPwd" type="text" placeholder="旧密码"/>
 		<input name="newPwd" type="text" placeholder="新密码"/>
 		<input name="_method" type="hidden" value="put" />
 		<input type="submit" value="submit"/>
 	</form>
 	<br/>
 	<form action="${pageContext.request.contextPath }/help/checkNewVersion" method="get">
 	 	<label>checkNewVersion</label>
 		<input type="submit" value="submit"/>
 	</form>
 	<br/>
 	<form action="${pageContext.request.contextPath }/user/feedback" method="post">
 	 	<label>feedback</label>
 		<input name="userID" type="text" placeholder="用户ID"/>
 		<input name="ideaText" type="text"  placeholder="意见反馈"/>
 		<input name="_method" type="hidden" value="put" />
 		<input type="submit" value="submit"/>
 	</form>
  
 		<form action="${pageContext.request.contextPath }/user/uploadPicture" method="POST" enctype="multipart/form-data">
		File: <input type="file" name="file"/>
		Desc: <input type="text" name="userID"/>
		<input type="submit" value="Submit"/>
	</form> <br/>
	
		<form action="${pageContext.request.contextPath }/user/changePhone" method="POST" >
		<label>changePhone</label>
		 <input type="text" name="newPhoneNum" placeholder="新手机号"/>
		<input type="text" name="userID" placeholder="用户ID"/>
			<input type="password" name="password" placeholder="密码"/>
		<input type="submit" value="Submit"/>
	</form>
	<br/>
	<form action="${pageContext.request.contextPath}/picture/getHeadList" method="post">
		<label>获取图片集合</label>
		<input type="submit" value="send it"/>
	</form>
 	<br/>
 	<a href="${pageContext.request.contextPath }/excl/interfacerequest.xls"><font size="20" color="red">请求接口说明下载</font></a>
 	
 	<br/>
 	<form action="${pageContext.request.contextPath }/picture/upload" method="post" enctype="multipart/form-data">
 		图片顺序<input type="text" name="partNum"/>
 		图片点击链接<input type="text" name="href"/>
 		上传图片<input type="file" name="picture"/>
 		<input type="submit" value="save"/>
 	</form>
 	<img alt="aa" src="${pageContext.request.contextPath }/image/pic-1.jpg">
 	<img alt="aa" src="${pageContext.request.contextPath }/image/pic-2.jpg">
 	<img alt="aa" src="${pageContext.request.contextPath }/image/pic-3.jpg">
 	<br/>
 	<label>收益推荐表</label>
 	<form action="${pageContext.request.contextPath }/incomeRecommendList" method="post"> 
 			<label>收益类型</label>
 			<select name="IncomeType">
 				<option value="0">月收益</option>
 				<option value="1">年收益</option>
 				<option value="2">优选收益</option>
 				<option value="3">总收益</option>
 			</select><br/>
 			<label>请求页数</label>
 			<input type="text" name="pageNum"/><br/>
 			<label>页内数量</label>
 			<input type="text" name="pageSize"/>
 			<br/><input type="submit" value="提交"/>
 			</form>
 	<br/>
 	<label>英雄榜</label>
 	<form action="${pageContext.request.contextPath }/bestIncomeList" method="post"> 
 			 
 			<label>请求页数</label>
 			<input type="text" name="pageNum"/><br/>
 			<label>页内数量</label>
 			<input type="text" name="pageSize"/>
 			<br/><input type="submit" value="提交"/>
 			</form>
 	<br/>
 	<label>用户组合列表</label>
 	<form action="${pageContext.request.contextPath }/userGroups" method="post"> 
 			
 			<label>用户名称</label>
 			<input type="text" name="userName"/><br/>
 			<label>请求页数</label>
 			<input type="text" name="pageNum"/><br/>
 			<label>页内数量</label>
 			<input type="text" name="pageSize"/>
 			<br/><input type="submit" value="提交"/>
 			</form>
 	<br/>
 	
 	<label>组合细节</label>
  	<form action="${pageContext.request.contextPath }/group/Detail" method="post">
 		
 			<label>请求组合名称或者组合ID</label>
 			<input type="text" name="groupNameOrId"/><br/>
 			 
 			 <input type="submit" value="提交"/>
 	</form>
 	<br/>
 	<label>组合比例</label>
  	<form action="${pageContext.request.contextPath }/group/Component" method="post">
 		
 			<label>请求组合名称或者组合ID</label>
 			<input type="text" name="groupNameOrId"/><br/>
 			 
 			 <input type="submit" value="提交"/>
 	</form>
 	<label>组合分析</label>
  	<form action="${pageContext.request.contextPath }/group/Analyse" method="post">
 		
 			<label>请求组合名称或者组合ID</label>
 			<input type="text" name="groupNameOrId"/><br/>
 			 
 			 <input type="submit" value="提交"/>
 	</form>
 	<label>组合评论</label>
  	<form action="${pageContext.request.contextPath }/group/Recommend" method="post">
 		
 			<label>请求组合名称或者组合ID</label>
 			<input type="text" name="groupNameOrId"/><br/>
 			 
 			 <input type="submit" value="提交"/>
 	</form>
 	<br/>
 	
 	<label>当前收益列表</label>
 	<form action="${pageContext.request.contextPath }/currentIncomeList" method="post">
 	<label>收益类型</label>
 			<select name="type">
 				<option value="0">本周</option>
 				<option value="1">本月</option>
 				<option value="2">本年</option>
 				<option value="3">创建以来</option>
 			</select><br/>
 			<label>请求组合名称或者组合ID</label>
 			<input type="text" name="groupNameOrId"/><br/>
 		<label>请求页数</label>
 			<input type="text" name="pageNum"/><br/>
 			<label>页内数量</label>
 			<input type="text" name="pageSize"/>
 			 <input type="submit" value="提交"/>
 	</form>
  	<a href="Jdtoa.jsp">查看行情接口</a>
  	<a href="group.jsp">组合接口</a>
  </div>
</body>
</html>