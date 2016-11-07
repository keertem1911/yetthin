<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/bootstrap.css">
	
</head>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.1.0.min.js"></script>
	<script type="text/javascript">
		$(function(){
			var url =$("#getList").val();
			 
			$.post(url,{},function(msg){
				console.log(msg.value);
				for(var i=0;i<msg.value.length;++i){
					$("#pic-"+msg.value[i].id).val(msg.value[i].href);
				}
			})
		})
	</script>
<body>
  <input type="hidden" id="getList" value="${pageContext.request.contextPath}/picture/getHeadList"/>
 
  <div class="row">
  <div class="col-xs-6 col-md-3">
    <a href="#" class="thumbnail">
    	<label>第一张 点击链接为:<input  id="pic-1"></input></label>
      <img data-src="holder.js/100%x180" alt="第一张" src="${pageContext.request.contextPath }/image/pic-1.jpg">
    </a>
  </div>
    <div class="col-xs-6 col-md-3">
    <a href="#" class="thumbnail">
    		<label>第二张 点击链接为:<input href="#" id="pic-2"></input></label>
      <img data-src="holder.js/100%x180" alt="第二张" src="${pageContext.request.contextPath }/image/pic-2.jpg">
    </a>
  </div>
   <div class="col-xs-6 col-md-3">
    <a href="#" class="thumbnail">
    	<label>第三张 点击链接为:<input href="#" id="pic-3"></input></label>
      <img data-src="holder.js/100%x180" alt="第三张" src="${pageContext.request.contextPath }/image/pic-3.jpg">
    </a>
  </div>
</div>
 
</body>
</html>