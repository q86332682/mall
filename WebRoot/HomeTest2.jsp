<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'HomeTest.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		//加载热门关键词
		$.ajax({
			type : 'get',
			url : '${pageContext.request.contextPath}/hometest/loadHotSearchlist.action',
			success : function(data){
				console.log("加载热门关键词!");
			}
		});
		
		//加载人气排行榜
		$.ajax({
			type : 'get',
			url : '${pageContext.request.contextPath}/hometest/getGoodsByPopRank.action',
			success : function(data){
				console.log("加载人气排行榜!");
			}
		});
		
		//加载推荐排行榜
		$.ajax({
			type : 'get',
			url : '${pageContext.request.contextPath}/hometest/getGoodsByRecommend.action',
			success : function(data){
				console.log("加载推荐排行榜!");
			}
		});
		
		//加载热销排行榜
		$.ajax({
			type : 'get',
			url : '${pageContext.request.contextPath}/hometest/getGoodsBySellhot.action',
			success : function(data){
				console.log("加载热销排行榜!");
			}
		});
		
		//加载分类列表
		$.ajax({
			type : 'get',
			url : '${pageContext.request.contextPath}/hometest/getCategorylist.action',
			success : function(data){
				console.log("加载分类列表!");
			}
		});
		
		//加载推荐分类
		$.ajax({
			type : 'get',
			url : '${pageContext.request.contextPath}/hometest/getRecommendCategory.action',
			success : function(data){
				console.log("加载推荐分类!");
			}
		});
		
		//加载推荐品牌
		$.ajax({
			type : 'get',
			url : '${pageContext.request.contextPath}/hometest/getRecommendBrand.action',
			success : function(data){
				console.log("加载推荐品牌!");
			}
		});
	</script>
  </head>
  
  <body>
    This is my JSP page. <br>
  </body>
</html>
