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
	<script type="text/javascript">
			$(function(){
				
				var url =window.location.href;
				 url=url.substr(0, url.lastIndexOf("/"));
				 url=url.substr(0, url.lastIndexOf("/")+1);
				
				$("#clear-table").click(function(){
					$("#tbody-div").empty();
					
				})
				$("#gobtn").click(function(){
					var phone=$("#phoneNum").val();
					if(phone==""){
						alert("请输入号码");
					}else{
						$.ajax({
							url:url+"admin/selectByphoneNum/"+phone.trim(),
							type:"get",
							data:{},
							dataType:"json",
							success:function(msg){
								if(msg.error!=undefined){
									alert(msg.error);
								}else{
									var user=msg.user;
									var html="";
									html+="<tr><td><span>"+user.userId+"</span></td>"+
									"<td><span>"+user.phoneNum+"</span></td>"+
									"<td><span>"+user.myMoney+"</span></td>"+
									"<td><span>"+user.email+"</span></td>"+
									"<td><span>"+user.password+"</span></td>"+
									"<td><span>"+user.jpushId+"</span></td>"+
									"<td><span>"+user.status+"</span></td>"+
									"<td><a href='"+url+"admin/deleteUser?userId="+user.userId+"'><span class='label label-warning'>delete</span></a></td></tr>";
									
									$("#tbody-div").append(html);
								}
							}
						})
					}
				})
			})
			
	</script>
</head>
<body>
	<div class="input-group input-group-lg col-xs-4">
  <!-- <span class="input-group-addon" id="sizing-addon1"></span> -->
  <input type="text" class="form-control" id="phoneNum" placeholder="PhoneNum" aria-describedby="sizing-addon1"/>
	 <span class="input-group-btn">
        <button class="btn btn-primary" id="gobtn"type="button">Go!</button>
      </span>
      <span class="input-group-btn">
      <button type="button" class="btn btn-danger clearbtn" id="clear-table">Clear</button>
      </span>	
	</div>
	<table class=" table table-hover" border="1" bordercolor="black" cellpadding="1" cellspacing="1">
				<thead>
				<tr>
					<td><span>userID</span></td>
					<td><span>phoneNum</span></td>
					<td><span>myMoney</span></td>
					<td><span>email</span></td>
					<td><span>password</span></td>
					<td><span>jpushId</span></td>
					<td><span>status</span></td>
					<td><span>删除</span></td>
				</tr>
				</thead>
				<tbody id="tbody-div">
				 
				
				</tbody>
			</table>
</body>
</html>