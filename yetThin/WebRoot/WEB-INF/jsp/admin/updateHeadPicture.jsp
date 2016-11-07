<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/bootstrap.css">
	<style>
		#showlist{
		}
	</style>
</head>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-form.js"></script>
<script type="text/javascript">
$(function(){
	
		$("#form").submit(function(){
			var ext= "jpg";
			var f=$("#picture").val();
			if(f==""){
				alert("请上传图片");
				return false;
			}else{
				f=f.substr(f.lastIndexOf('.')+1).toLowerCase();
				if(f==ext){
					var mapping ={
							url:$(this).attr("action"),
							dataType:"json",
							type:"post",
							contentType: "application/json; charset=utf-8", 
							data:{
								"partNum":$("#partNum").val(),
								"href":$("#href").val(),
								"picture":$("#picture").val() 
								},
							success:function(msg){
								if(msg==200){
								alert("保存成功");
							//	$("#showlist").load($("#showurl").val());
								}
								else
								alert("保存失败");
							}
					};
					$("#form").ajaxSubmit(mapping);	
					}else{
					alert("上传格式必须为JPG");
					return false;
				}
			}
			
			 return false;
		})
		
	
})
</script>
<body>

<%-- <form action="${pageContext.request.contextPath }/picture/upload" method="post" enctype="multipart/form-data">
 		图片顺序<input type="text" name="partNum"/>
 		图片点击链接<input type="text" name="href"/>
 		上传图片<input type="file" name="picture"/>
 		<input type="submit" value="save"/>
 	</form> --%>
<form id="form" action="${pageContext.request.contextPath }/picture/upload" method="post" enctype="multipart/form-data">
  <div class="form-group">
    <label for="exampleInputEmail1">轮播图次序</label>
    	<input  type="text" class="form-control" name="partNum" id="partNum"/>
    
    
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">图片链接地址</label>
    <input type="text" class="form-control" name="href"  id="href" placeholder="url">
  </div>
  <div class="form-group">
    <label for="exampleInputFile">图片上传</label>
    <input type="file" name="picture" id="picture">
  </div>
   <div>
  
   </div>
  <button type="submit" class="btn btn-default" >Submit</button>
</form>
  <a href="${pageContext.request.contextPath }/pageRequest/showpicture" >查看上传结果</a>
</body>
</html>