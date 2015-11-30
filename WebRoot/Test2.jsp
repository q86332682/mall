<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Test2.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		//加载导航列表
		$.ajax({
			type : 'get',
			url : 'home/loadNavigatelist.action',
			success : function(data){
				console.log(data);
			}
		});
		
		//加载热门关键词
		$.ajax({
			type : 'get',
			url : 'home/loadHotkeywordlist.action',
			success : function(data){
				console.log(data);
			}
		});
		

		//加载分类列表
		$.ajax({
			type : 'get',
			url : 'home/loadCategorylist.action',
			success : function(data){
				console.log(data);
			}
		});
		
		//加载广告列表
		$.ajax({
			type : 'get',
			url : 'home/loadAdvertlist.action',
			success : function(data){
				console.log(data);
			}
		});
		
		//加载热门和广告下方商品列表
		$.ajax({
			type : 'get',
			url : 'home/loadGoodslist1.action',
			success : function(data){
				console.log(data);
			}
		});
		
		//加载推荐商品列表
		$.ajax({
			type : 'get',
			url : 'home/loadGoodslist2.action',
			success : function(data){
				console.log(data);
			}
		});
	</script>
  </head>
  
  <body>
    This is my JSP page. <br>
  </body>
</html>
