<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/bootstrap.css">
<style type="text/css">
	.display{
		margin-left: 100px;
	}
	form {
		vertical-align: center;
		text-align: left;
		margin-left: 20px;
		
	}
	.main{
	margin-top: 20px;
	}
	table{
		text-align: center;	
	}
	 
	.big1{
		text-align:left;
		
		width:250px;
	}
	.small1{
		width:50px;
	}
	.small2{
		width:30px;	
	}
	td{
		margin-top: 2px;
	}
</style>
<title>行情接口展示</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jQuery.timers.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
	<script type="text/javascript">
			$(function(){
				function makeLevel1TableOFIndex(msg){
					var html="<tr class='big1 active'>";
					// 指数累加
					for(var i=0;i<msg.index.length;++i){
						
						html+="<td >";
						html+="<h2><span color='red'>"+msg.index[i].name+"</span></h2><br/>";
						if(msg.index[i].increase=="false"){
						html+="<h3><span style='color:green' >"+msg.index[i].growth+"</span></h3>&nbsp;&nbsp;";
						html+="<h4><span style='color:green'>"+msg.index[i].price+"</span></h4><br/>";
						html+="<h4><span style='color:green'>-"+msg.index[i].growthRate+"%</span></h4>";
						}
						else{
							html+="<h3><span style='color:red' >"+msg.index[i].growth+"</span></h3>&nbsp;&nbsp;";
							html+="<h4><span style='color:red'>+"+msg.index[i].price+"</span></h4><br/>";
							html+="<h4><span style='color:red'>+"+msg.index[i].growthRate+"%</span></h4>";}
						html+="</td>";
					}// for 指数累加结束
					 html+="</tr>";
					 return html;
				}
				function makeLevel1TableOFSymbol(msg){
					var html="";
					 for(var i=0;i<msg.item.length;++i){
						 // 二级标题 
						 html+="<tr style='widhth:50px' class='active'>";
						 
						 html+="<td colspan='6'>"+msg.item[i]["group"]+"</td>";
						 var index1 = msg.item[i]["marketId"].split(":")[1];
						 html+="</tr>";
						 
						 var color="red";
						 var plus ="+";
						 // 具体股票
						 for(var j=0;j<msg.item[i].index.length;++j){
							 // 判断涨跌
							if(msg.item[i].index[j].increase=="false"){
								color="green";
								plus="-";
							}else{
								color="red";
								plus="+";
							}
						 html+="<tr colspan='6' style='widhth:30px' class='active'>";
						 
						 html+="<td colspan='2'><span  >"+msg.item[i].index[j].name+":"+msg.item[i].index[j].stockID+"</span></td>";
						 html+="<td colspan='2'><span style='color:"+color+"' >"+msg.item[i].index[j].price+"</span></td>";
						 if(index1=="1"||index1=="0"){
						 html+="<td colspan='2'><span style='color:"+color+"' >"+msg.item[i].index[j].updown+"</span></td>";
						
						 html+="<td colspan='1'><span style='color:"+color+"' >"+plus+msg.item[i].index[j].rate+"%</span></td>";
						 }else{
							 html+="<td colspan='3'><span style='color:"+color+"' >+"+msg.item[i].index[j].exchange+"%</span></td>";
								
						 }
						
						 
						 html+="<td colspan='1'><input type='hidden' value='"+msg.item[i].index[j].stockID+"'/><a href='javascript:void(0)' >详情</a></td>";
						 
						 html+="</tr>";
						 }
						 html+="<td colspan='2'></td><td colspan='2'><input type='hidden' value='"+msg.item[i]["marketId"]+"'><a href='javascript:void(0);'>更多</a></td><td colspan='2'></td>";
						 
					 }// 具体股票添加结束
					return html;
				}
				function makeLevel2Detail(msg){
					var html="";
					var plus="+";
					var rate="%";
					var color="red";
					var level1=msg.item.level1;
					if(level1.increate=="false"){
						color="green";
						plus="-";
					}
					html+="<tr colspan='5' style='width:100px,height:50px' class='active'>";
					html+="<td colspan='1'>"
					+"<h3><span>"+level1.last+"</span></h3></td><td colspan='2'>"
					+"<h4><span style='color:"+color+"'>"+plus+level1.updown+"</span></h4>"
					+"<h5><span style='color:"+color+"'>"+plus+level1.rate+"%</span></h5>";
					+"</td>";
					html+="<td colspan='1'>"
					+"<h4>开:&nbsp;"+level1.open+"</h4>"
					+"<h4>低:&nbsp;"+level1.low+"</h4>"
					+"<h4>高:&nbsp;"+level1.height+"</h4>"
					+"<h4>换:&nbsp;"+level1.exchange+"%</h4></td>"
					+"<td colspan='1'>"
					+"<h4>额"+level1.totlesum+"万<h4>"
					+"<h4>量"+level1.volume+"个</h4>"
					+"</td>";
					
					html+="</tr>";
					
					return html;
				}
				function makeLevel2Depth(msg){
					var level2=msg.item.level2;
					var html="";
					var cnt=0;
					var depth=["sell","buy"];
					for(var i=5;i>0;--i){
					html+="<tr colspan='5' style='widhth:200px,height:50px' class='active'>";
					html+="<td colspan='2'>卖"+i+"</td>";
					html+="<td colspan='2'>"+level2["sell"+i][0]+"</td>";
					html+="<td colspan='1'>"+level2["sell"+i][1]+"</td>";
					html+="</tr>";
					}
					for(var i=1;i<6;++i){
						html+="<tr colspan='5' style='widhth:200px,height:50px' class='active'>";
						
						html+="<td colspan='2'>买"+i+"</td>";
						html+="<td colspan='2'>"+level2["buy"+i][0]+"</td>";
						html+="<td colspan='1'>"+level2["buy"+i][1]+"</td>";
						html+="</tr>";	
					}
					return html;
				}
				function shenzhenFun(){
					var url =$("#shenzhen").attr("action");
					var values={};
					var inputs=$(".shenzhen1");
					for(var i=0;i<inputs.length;++i){
							values[$(inputs[i]).attr("name")]=$(inputs[i]).val();
					}
					console.log(values);
					
					$.post(url,values,function(msg){
						$(".level1").empty();
						var html="<table border=1 style='width:85%' class='table-bordered table-hover' >";
						console.log(msg);
						html+=makeLevel1TableOFIndex(msg);
						html+=makeLevel1TableOFSymbol(msg);
						
						 html+="</table>";
						 $(".level1").append(html);
						 $("a").click(function(){
							 var tr=$(this).parent();
						//	 console.log($(tr).find("input").eq(0).val());
							var marketId=$(tr).children(":first").val();
							if(marketId.length=="3"){
							var url=$("#shenzhen").attr("action");
							 	$.post(url,{"begin":0,"end":20,"marketCode":marketId},function(msg){
							 		console.log(msg);
							 		$(".level1").empty();
							 		var html="<table border=1 style='width:85%' class='table-bordered table-hover' >";
							 		html+=makeLevel1TableOFSymbol(msg);
							 		html+="</table>";
							 		$(".level1").append(html);
							 	});
							}else{
								var url=$("#getLevel2").attr("action");
							 	$.post(url,{"symbol":marketId},function(msg){
							 		console.log(msg);
							 		$(".level2").empty();
							 		var html="<table border=1 style='width:85%' class='table-bordered table-hover' >";
							 		html+=makeLevel2Detail(msg);
							 		html+="</table>";
							 		 html+="<table border=1 style='width:85%' class='table-bordered table-hover' >";
							 		html+=makeLevel2Depth(msg);
							 		html+="</table>";							 		
							 		$(".level2").append(html);
							 	});
							}
						 });
					});
				}
			/* 	$("#shenzhen-sub").click(function(){
						 shenzhenFun();
					
				}); */
				$('body').everyTime('6s',function(){
					 shenzhenFun();
				 });
				 
			})
	</script>
</head>
<body>
	<div>
	<br/>
	<form action="${pageContext.request.contextPath }/jtdoa/shenzhen" method="post" id="shenzhen">
		 <label>/jtdoa/shenzhen  沪深股市接口 </label>
		 <input class="shenzhen1" name="begin" type="text" value="0" placeholder="起始位置  start with 0 默认为0 可不选"/>
		 <input class="shenzhen1" name="end" type="text" value="9" placeholder="结束位置   默认为9 可不选"/>
		 <input class="shenzhen1" type="input" name="indexMarket" value="true" id="Level1indexMarket"/> 
		 <input class="shenzhen1" type="input" name="master" value="true" id="Level1Master"/> 
		
		 <label>股市选择及属性 可不选 默认为沪深 的 涨幅,跌幅,换手率</label>
		 <select name="marketCode"  class="shenzhen1">
		 	 
		 	<option value="0:0" >沪深:涨幅(0:0)</option>
		 	<option value="0:1">沪深:跌幅(0:1)</option>
		 	<option value="0:2">沪深:换手率(0:2)</option>
		 	<option value="0:0,1">沪深:涨幅,跌幅(0:0,1)</option>
		 	<option value="0:0,1,2" selected="selected">沪深:涨幅,跌幅,换手率(0:0,1,2)</option>
		 </select>
		<input type="button" value="get" id="shenzhen-sub"/>
	</form><br/>
	<form action="${pageContext.request.contextPath }/jtdoa/getLevel2" method="post" id="getLevel2">
		<label>摆单接口 </label>
		<input type="text" name="symbol" placeholder="格式:000001.SH"/>
		<input type="submit" value="submit" id="getLevel2btn"/>
	</form>
	  <form method="post" action="${pageContext.request.contextPath }/jtdoa/getLevel2Detail">
	 <label>获取交易详单</label>
		<input type="text" name="symbol" placeholder="股票代码+'.'+市场"></input>
		<input type="submit" value="get"/>
	</form>  getIndexDetail
	 <form method="post" action="${pageContext.request.contextPath }/jtdoa/getIndexDetail">
	 <label>获取指数详单</label>
		<input type="text" name="symbol" placeholder="股票代码+'.'+市场" value="000001.SH"></input>
		<input type="submit" value="get"/>
	</form>getLevel2DetailTick  
	 </br>
	<form action="${pageContext.request.contextPath }/jtdoa/getStockIndexList" method="post">
		 <label>/jtdoa/getStockIndexList  沪深指数接口 </label>
		 <input name="begin" type="text" value="0" placeholder="起始位置  start with 0 默认为0 可不选"/>
		 <input name="end" type="text" value="6" placeholder="结束位置   默认为9 可不选"/>
		 <label>是否指数详细</label>
		 <input type="input" name="master" value="true"  /> 
		
		 <label>股市选择及属性 可不选 默认为国内沪深指数</label>
		 <select name="marketCode" >
		 	<option value="0">默认(0)</option>
		 	<option value="0">沪深指数(0)</option>
		 	<!-- <option value="1">沪深:跌幅(0:1)</option>
		 	<option value="0:2">沪深:换手率(0:2)</option>
		 	<option value="0:0,1">沪深:涨幅,跌幅(0:0,1)</option>
		 	<option value="0:0,1,2">沪深:涨幅,跌幅,换手率(0:0,1,2)</option>
		  --></select>
		<input type="submit" value="get" />
	</form><br/>
	<form action="${pageContext.request.contextPath }/jtdoa/getHistoryBar" method="post">
		 <label>/jtdoa/getHistoryBar  K线历史接口 </label>
		 <input name="symbol" type="text" value="002362.SZ" placeholder="股票代码"/>
		 <label>K线条数 最少</label>
		 <input type="text" name="barNum"  placeholder="K线条数 最少" /> 
		
		 <input type="text" name="currenyTime" value="2016:08:22  12:21:23" placeholder="时间格式2016:09:01 12:21:23"/>
    	  <label>K线时间周期值</label>
    	  <input type="text" name="cycNum" value="1" placeholder="K线时间周期 数字 (上面的单位)"/>
		 <label>K线类型(单位)</label>
		 <select name="barType" >
		 	<option value="0">单位秒</option>
		 	<option value="1">单位分</option>
		 	 <option value="2">单位天</option>
		 	<option value="3">单位周</option>
		 	<option value="4">单位月</option>
		 	<option value="5">单位季度</option>
		 	<option value="6">单位半年</option>
		 	<option value="7">单位年</option>
 		   </select>
		<input type="submit" value="get" />
	</form><br/>
 	<form action="${pageContext.request.contextPath }/jtdoa/getStockIndustryPlate"
 	method="post" 
 	>
 		<label>获取行情数据预览型</label>
		<input type="text" value="0" placeholder="起始值" name="begin"/>
		<input type="text" value="10" placeholder="终止值" name="end"/>
		<input type="text" value="" placeholder="起始时间" name="time"/>
		<input type="text" value="" placeholder="持续天数" name="timeNum"/>
		
		<input type="submit" value="submits"/>
		</form>
 	<form action="${pageContext.request.contextPath }/jtdoa/getStockIndustryDK"
 	method="post" 
 	>
 		<label>获取行情数据K日k线</label>
 		
		<input type="text" value="1" placeholder="行业代码" name="sid"/>
		<input type="text" value="" placeholder="起始时间" name="time"/>
		<input type="text" value="" placeholder="持续天数" name="timeNum"/>
		
		<input type="submit" value="submits"/>
		</form>
	</div>
	<div class="display">
		<div class="level1"></div>
		<div class="level2"></div>
		<div class="level2detail"></div>
	</div>
	
	
	
	
	
</body>
</html>